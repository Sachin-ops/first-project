package com.ExpenseService.ExpenseService.Exceptions;

public class NoExpensePresent extends RuntimeException{

	
	public NoExpensePresent(String msg)
	{
		super(msg);
	}
}
