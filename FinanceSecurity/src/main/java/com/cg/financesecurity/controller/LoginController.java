package com.cg.financesecurity.controller;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.financesecurity.Entity.LoginRequest;
import com.cg.financesecurity.config.FinanceUsernamePwdAuthenticationProvider;
import com.cg.financesecurity.repository.UserRepo;
import com.cg.financesecurity.service.JwtService;
import com.cg.financesecurity.Entity.*;
@RestController
public class LoginController {

	@Autowired
private FinanceUsernamePwdAuthenticationProvider financeAuthProvider;
	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody LoginRequest auth)
	{
		Authentication authentication= financeAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPwd()));
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(auth.getEmail());
			
		}
		else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}
//	
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//	  Optional<User>  = userRepo.findByEmail(loginRequest.getEmail());  // Fetch by email
//	    if (user.isPresent() && encoder.matches(loginRequest.getPwd(), user.get().getPwd())) {
//	        String token = jwtService.generateToken(user.get().getUsername());
//	        return ResponseEntity.ok(token);
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//	    }
//	}
//	
	


	
}
