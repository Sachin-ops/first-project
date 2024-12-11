package com.UserService.UserService.External.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "FINANCE-SECURITY")
public interface FinanceSecurityFeign {
	@GetMapping("/validateToken")
	ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token);

}
