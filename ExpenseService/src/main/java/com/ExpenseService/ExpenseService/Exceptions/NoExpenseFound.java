package com.ExpenseService.ExpenseService.Exceptions;

public class NoExpenseFound extends RuntimeException {

	public NoExpenseFound(String msg) {
		super(msg);
	}
}
