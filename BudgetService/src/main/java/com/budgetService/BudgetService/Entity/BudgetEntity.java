package com.budgetService.BudgetService.Entity;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="Budget")
@jakarta.persistence.Entity
public class BudgetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
	@Column(name="Name")
		private String name;
	@Column(name="Description")
		private String description;
	
	@Column(name ="userId") // Ensures NOT NULL in the database
    private Long userId;
	

//	@ManyToOne
//	private Catego category;
//	

	
	
}
