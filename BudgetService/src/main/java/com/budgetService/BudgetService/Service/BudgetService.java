package com.budgetService.BudgetService.Service;

import java.util.List;

import com.budgetService.BudgetService.Entity.BudgetEntity;

public interface BudgetService {

	BudgetEntity createBudget(BudgetEntity budget);
    BudgetEntity getBudget(Long id);
    List<BudgetEntity>getAllBudget();
    BudgetEntity updateBudget(Long id, BudgetEntity budget);
    void deleteBudget(Long id);
	List<BudgetEntity> getBudgetByUserid(Long userId);
}
