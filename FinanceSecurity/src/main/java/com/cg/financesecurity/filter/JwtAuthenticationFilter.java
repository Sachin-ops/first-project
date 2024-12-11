package com.cg.financesecurity.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cg.financesecurity.Entity.Auth;
import com.cg.financesecurity.Entity.User;
import com.cg.financesecurity.constants.SecurityConstants;
import com.cg.financesecurity.repository.UserRepo;
import com.cg.financesecurity.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@DependsOn("jwtService")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepo userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestHeader = request.getHeader(SecurityConstants.JWT_HEADER);
		logger.info("Header: {}", requestHeader);
		String username = null;
		String token = null;

		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			token = requestHeader.substring(7);
			username = jwtService.extractUsername(token);
		} else {
			logger.info("Invalid Header value!! ");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// Optional<User> user = userRepo.findByEmail(username);
			Optional<User> user = userRepo.findByEmail(username);
			logger.info("username: {}", username);
			if (user.isPresent() && jwtService.validateToken(token, user.get())) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
						getGrantedAuthority(user.get().getAuthorities()));
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);

			}
		}

		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/login") || request.getServletPath().equals("/register");
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthority(Set<Auth> authorities) {
		Collection<GrantedAuthority> authList = new ArrayList<>();

		for (Auth auth : authorities) {
			authList.add(new SimpleGrantedAuthority(auth.getName()));
		}
		return authList;
	}

}
