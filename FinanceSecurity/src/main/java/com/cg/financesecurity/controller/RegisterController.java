package com.cg.financesecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.financesecurity.Entity.User;
import com.cg.financesecurity.repository.UserRepo;

@RestController
public class RegisterController {
	
     @Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user)
	{
		user.setPwd(encoder.encode(user.getPwd()));
		 userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("user saved");
		
	}
	
}
