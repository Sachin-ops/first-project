package com.budgetService.BudgetService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgetService.BudgetService.Entity.BudgetEntity;
import com.budgetService.BudgetService.Exception.NoBudgetPresent;
import com.budgetService.BudgetService.Service.BudgetService;

@RestController
@RequestMapping("/budgets")
public class BudgetConroller {

	@Autowired
	private BudgetService serv;

	@PostMapping
	public ResponseEntity<?> createBudget(@RequestBody BudgetEntity budgetEntity) {
//	    if (budgetEntity.getUserId() == null) {
//	        // Return a ResponseEntity with a proper message (String) when validation fails
//	        return ResponseEntity.badRequest().body("User ID must be provided");
//	    }
		// Save the budget if the userId is present
		BudgetEntity savedBudget = serv.createBudget(budgetEntity);
		return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BudgetEntity> updateBudget(@PathVariable("id") Long id, @RequestBody BudgetEntity budget) {
		return new ResponseEntity<BudgetEntity>(serv.updateBudget(id, budget), HttpStatus.ACCEPTED);
	}

//
//	@GetMapping("/{BudgetId}")
//	public ResponseEntity<BudgetEntity> getBudget(@PathVariable("BudgetId") Long BudgetId) {
//	    BudgetEntity budget = serv.getBudgetById(BudgetId);
//	    if (budget != null) {
//	        return new ResponseEntity<>(budget, HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
//	    }
//	}
	@GetMapping("/{id}")
	public BudgetEntity getBudget(@PathVariable("id") Long id) {
		return serv.getBudget(id);
	}

	@GetMapping
	public ResponseEntity<List<BudgetEntity>> getAllBudget() {
		return new ResponseEntity<List<BudgetEntity>>(serv.getAllBudget(), HttpStatus.FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
		try {
			serv.deleteBudget(id);
			return ResponseEntity.noContent().build();
		} catch (NoBudgetPresent e) {
			// TODO: handle exception
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("users/{userId}")
	public ResponseEntity<List<BudgetEntity>> getBudgetByUserid(@PathVariable("userId") Long userId) {
		List<BudgetEntity> budget = serv.getBudgetByUserid(userId);
		return new ResponseEntity<>(budget, HttpStatus.OK);
	}
}
