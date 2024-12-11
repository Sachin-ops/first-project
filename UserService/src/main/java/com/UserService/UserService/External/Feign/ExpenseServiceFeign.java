package com.UserService.UserService.External.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.UserService.UserService.Entity.Expense;

@FeignClient("EXPENSE-SERVICE")
public interface ExpenseServiceFeign {
//
//	@GetMapping("/Expenses/users/{userId}")
//	Expense getExpenseById(@PathVariable() Long userID);
	@GetMapping("/Expenses/users/{userId}")
	public ResponseEntity<List<Expense>> getExpenseByUserid(@PathVariable Long userId);
	
	@GetMapping("/Expenses/budgets/{id}")
	public ResponseEntity<List<Expense>> getExpensesByBudgetId(@PathVariable Long id) ;

}
