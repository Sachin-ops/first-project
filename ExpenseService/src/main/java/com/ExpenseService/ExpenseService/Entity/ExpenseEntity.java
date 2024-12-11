package com.ExpenseService.ExpenseService.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Expense")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long expenseId;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@NotNull(message="Date cannot be null")
	private LocalDate expenseDate;


	private String description;
	
	@NotNull(message = "amount cannot be null")
	private Double amount;
	
	private String type;
	@NotNull(message = "User id cannot be null")
	private Long userId;
	private Long id;
//	@ManyToOne
//	private UserEntity userEntity;
//	
//	@ManyToOne
//	private CategoryEntity category;
//	

	//expense id (PK)     date       description   user id   category id
	//1001            9-10-2024  "visting home"    1              4
	

}
