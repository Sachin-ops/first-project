package com.UserService.UserService.Entity;

import java.util.List;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Budget {

	private Long id;

		private String name;
	
		private String description;
	 
	private Long userId;
	private Long expenseId;
	private Long categoryId;
	private Expense expense;
	private List<Expense> expenses;
	public void setExpenses(List<Expense> expenses) {
	    this.expenses = expenses;
	}

	
}
