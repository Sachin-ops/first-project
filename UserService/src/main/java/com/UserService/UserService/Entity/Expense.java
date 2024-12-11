package com.UserService.UserService.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Expense {

	
	private long Expenseid;
	
	private LocalDate expenseDate;
	
	private String description;
	private Expense expense;
	private Double amount;
	private String type;
	private Long userId;
	private Long categoryId;
	private Long id;
	private Budget budget;
	
}
