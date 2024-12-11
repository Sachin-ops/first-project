package com.ExpenseService.ExpenseService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ExpenseService.ExpenseService.Entity.ExpenseEntity;
import com.ExpenseService.ExpenseService.Exceptions.NoExpensePresent;
import com.ExpenseService.ExpenseService.Service.ExpenseService;

@RestController
@RequestMapping("/Expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/{expenseId}")
	public ResponseEntity<ExpenseEntity> getExpense(@PathVariable Long expenseId)

	{
		return new ResponseEntity<>(expenseService.getExpenses(expenseId), HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<ExpenseEntity> addExpense(@RequestBody ExpenseEntity expense) {
		return new ResponseEntity<ExpenseEntity>(expenseService.addExpenses(expense), HttpStatus.CREATED);
	}

	@DeleteMapping("/{expenseId}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {

		try {
			expenseService.deleteExpense(expenseId);
			return ResponseEntity.noContent().build();
		} catch (NoExpensePresent e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<ExpenseEntity>> getAllExpense() {
		return new ResponseEntity<>(expenseService.getAllExpenses(), HttpStatus.FOUND);
	}

	@PutMapping("/{expenseId}")
	public ResponseEntity<ExpenseEntity> updateExpense(@PathVariable Long expenseId, ExpenseEntity expense) {
		return new ResponseEntity<>(expenseService.updateExpenses(expenseId, expense), HttpStatus.ACCEPTED);
	}

	@GetMapping("users/{userId}")
	public ResponseEntity<List<ExpenseEntity>> getExpenseByUserid(@PathVariable Long userId) {
		List<ExpenseEntity> expense = expenseService.getExpenseByUserId(userId);
		return new ResponseEntity<>(expense, HttpStatus.OK);
	}

//	public ResponseEntity<List<ExpenseEntity>>getExpensesByBudgetId(@PathVariable Long id)
//	{
//	    
//	}

	@GetMapping("/budgets/{id}")
	public ResponseEntity<List<ExpenseEntity>> getExpensesByBudgetId(@PathVariable Long id) {
		List<ExpenseEntity> expenses = expenseService.getExpensesByBudgetId(id);
		if (expenses.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(expenses);
		}
		return ResponseEntity.ok(expenses);
	}

}
