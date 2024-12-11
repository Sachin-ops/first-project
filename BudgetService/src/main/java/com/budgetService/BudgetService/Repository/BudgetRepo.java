package com.budgetService.BudgetService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budgetService.BudgetService.Entity.BudgetEntity;
@Repository
public interface BudgetRepo extends JpaRepository<BudgetEntity, Long>{

	
	List<BudgetEntity> getBudgetByUserId(Long userId);
}
