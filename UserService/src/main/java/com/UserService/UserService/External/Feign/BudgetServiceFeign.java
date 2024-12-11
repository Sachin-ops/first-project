package com.UserService.UserService.External.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UserService.UserService.Entity.Budget;



@FeignClient(name = "BUDGET-SERVICE")
public interface BudgetServiceFeign {
//	@GetMapping("/budgets/{id}")
//	Budget getBudget(@PathVariable("id") Long id);
	@GetMapping("/budgets/{id}")
	public Budget getBudget(@PathVariable("id") Long id);
//	@GetMapping("/budgets/users/{userId}")
//	ResponseEntity<List<Budget>> getBudgetsByUserId(@PathVariable("userId") Long userId); // Match "userId"

	@GetMapping("/budgets/users/{userId}")
	public ResponseEntity<List<Budget>> getBudgetByUserid(@PathVariable("userId") Long userId) ;

}
