package com.ExpenseService.ExpenseService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ExpenseService.ExpenseService.Entity.ExpenseEntity;

public interface ExpenseRepo extends JpaRepository<ExpenseEntity, Long>{

	List<ExpenseEntity> findByUserId(Long userId);
	List<ExpenseEntity> findByBudgetId(Long budgetId);
}
