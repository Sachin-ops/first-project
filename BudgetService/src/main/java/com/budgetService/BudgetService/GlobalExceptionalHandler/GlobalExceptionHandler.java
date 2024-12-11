package com.budgetService.BudgetService.GlobalExceptionalHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.budgetService.BudgetService.Exception.NoBudgetPresent;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoBudgetPresent.class)
	public ResponseEntity<String> handleNoBudgetPresent(NoBudgetPresent ex)
	{
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		
	}
}
