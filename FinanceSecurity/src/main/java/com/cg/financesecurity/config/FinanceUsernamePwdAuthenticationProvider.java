package com.cg.financesecurity.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cg.financesecurity.Entity.Auth;
import com.cg.financesecurity.Entity.User;
import com.cg.financesecurity.repository.UserRepo;
import com.cg.financesecurity.service.JwtService;


@Component
public class FinanceUsernamePwdAuthenticationProvider implements AuthenticationProvider {

	@Autowired 
	PasswordEncoder encoder;

	@Autowired
	UserRepo userRepo;
	@Autowired
	private JwtService jwtService;
//	@Override
	public Authentication authenticate (Authentication authentication)throws AuthenticationException
	{
		String username= authentication.getName();
		String pwd = authentication.getCredentials().toString();
	Optional<User> user = userRepo.findByEmail(username);
	
		
		if(user.isPresent())
		{
			if(encoder.matches(pwd, user.get().getPwd()))
			{
				
				return new UsernamePasswordAuthenticationToken(username, pwd,
						getGrantedAuthority(user.get().getAuthorities()));
			}
			else
			{
				throw new BadCredentialsException("Invalid password");
			}}
			else {
				throw new BadCredentialsException("No User registered with this details");
			}
		}
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//	    String username = authentication.getName();
//	    String pwd = authentication.getCredentials().toString();
//
//	    // Directly retrieve the user and check for null
//	    Optional<User> user = userRepo.findByEmail(username);
//	    if (user == null) {
//	        throw new BadCredentialsException("No user registered with these details");
//	    }
//
//	    // Validate the password
//	    if (!encoder.matches(pwd, user.getPwd())) {
//	        throw new BadCredentialsException("Invalid password");
//	    }
//
//	    // Generate token and return authentication
//	    String token = jwtService.generateToken(username);
//	    return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthority(user.getAuthorities()));
//	}

	List<GrantedAuthority> getGrantedAuthority(Set<Auth> authorities) {
		List<GrantedAuthority> list_auth = new ArrayList<>();
		for (Auth auth : authorities) {
			list_auth.add(new SimpleGrantedAuthority(auth.getName()));

		}
		return list_auth;

	}
	@Override
	public boolean supports(Class<?> authentication) {

		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}