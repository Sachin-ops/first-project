package com.UserService.UserService.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Data
@NoArgsConstructor
public class UserLogin {

	public UserLogin(UserEntity user1, List<Category> categories, List<Budget> budgets, List<Expense> expenses) {
		super();
		this.user1 = user1;
		this.categories = categories;
		this.budgets = budgets;
		this.expenses = expenses;
	}
	private UserEntity user1;
	private List<Category> categories;
	private List<Budget> budgets;
	private List<Expense> expenses;
	
	
}
