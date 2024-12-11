package com.UserService.UserService.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.UserService.UserService.External.Feign.FinanceSecurityFeign;

@Service
public class JwtValidationService {

	@Autowired
	private FinanceSecurityFeign financeSecurityFeign;

	public boolean validateToken(String token) {
		try {
			ResponseEntity<Boolean> response = financeSecurityFeign.validateToken("Bearer " + token);
			return response.getBody() != null && response.getBody();
		} catch (Exception e) {
			return false; // Return false for invalid or failed token validation
		}
	}
}
