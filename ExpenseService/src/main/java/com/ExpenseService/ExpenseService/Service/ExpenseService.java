package com.ExpenseService.ExpenseService.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ExpenseService.ExpenseService.Entity.ExpenseEntity;

public interface ExpenseService  {

	
	ExpenseEntity getExpenses(Long expenseId);
	ExpenseEntity addExpenses(ExpenseEntity expense);
	ExpenseEntity updateExpenses(Long expenseId, ExpenseEntity expense);
	public void deleteExpense(Long expenseId);
	List<ExpenseEntity> getAllExpenses();
	List<ExpenseEntity>getExpenseByUserId(Long userId);
	List<ExpenseEntity>getExpensesByBudgetId(Long id);
   
}
