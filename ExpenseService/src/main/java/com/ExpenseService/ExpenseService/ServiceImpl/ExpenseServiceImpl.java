package com.ExpenseService.ExpenseService.ServiceImpl;


import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;
import java.lang.Comparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ExpenseService.ExpenseService.Entity.ExpenseEntity;
import com.ExpenseService.ExpenseService.Exceptions.NoExpenseFound;
import com.ExpenseService.ExpenseService.Exceptions.NoExpensePresent;
import com.ExpenseService.ExpenseService.Repository.ExpenseRepo;
import com.ExpenseService.ExpenseService.Service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepo expenseRepo;
	@Override
	public ExpenseEntity getExpenses(Long expenseId) {
		
		return expenseRepo.findById(expenseId).orElseThrow(()-> new NoExpensePresent("No expense present!!"));
	}

	@Override
	public ExpenseEntity addExpenses(ExpenseEntity expense) {
		
		return expenseRepo.save(expense);
	}

	@Override
	public ExpenseEntity updateExpenses(Long expenseId, ExpenseEntity expense) {
		ExpenseEntity existingExpense= expenseRepo.findById(expenseId).orElseThrow(()-> new NoExpensePresent("No expense to update!!"));
		existingExpense.setAmount(expense.getAmount());
		existingExpense.setDescription(expense.getDescription());
		existingExpense.setExpenseDate(expense.getExpenseDate());
		existingExpense.setType(expense.getType());
		
		return expenseRepo.save(existingExpense);
	}

//	@Override
//	public void deleteExpense(expenseId) {
//		expenseRepo.deleteById(expenseId);
//		
//	}

	@Override
	public List<ExpenseEntity> getAllExpenses() {
	    return expenseRepo.findAll().stream()
	            .sorted(Comparator.comparing(ExpenseEntity::getExpenseDate, Comparator.nullsLast(Comparator.reverseOrder())))
	            .collect(Collectors.toList());
	}



	@Override
	public List<ExpenseEntity> getExpenseByUserId(Long userId) {
	    List<ExpenseEntity> expenses = expenseRepo.findByUserId(userId);
	    if (expenses.isEmpty()) {
	        throw new NoExpenseFound("No Expense found for user ID: " + userId);
	    }
	    return expenses;
	}

	@Override
	public void deleteExpense(Long expenseId) {
		expenseRepo.deleteById(expenseId);
		
	}

	@Override
	public List<ExpenseEntity> getExpensesByBudgetId(Long id) {
		 List<ExpenseEntity> expense =expenseRepo.findByBudgetId(id);
		return expense;
	}

	

	
}
