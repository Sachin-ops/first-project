package com.UserService.UserService.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserService.UserService.Entity.Budget;
import com.UserService.UserService.Entity.Category;
import com.UserService.UserService.Entity.Expense;
import com.UserService.UserService.Entity.UserEntity;
import com.UserService.UserService.Entity.UserLogin;
import com.UserService.UserService.Exception.NoUserFound;
import com.UserService.UserService.Exception.UserNotFoundException;
import com.UserService.UserService.External.Feign.BudgetServiceFeign;
import com.UserService.UserService.External.Feign.CategoryServiceFeign;
import com.UserService.UserService.External.Feign.ExpenseServiceFeign;
import com.UserService.UserService.Repo.UserRepo;
import com.UserService.UserService.Service.UserService;
import com.UserService.UserService.ServiceImpl.JwtValidationService;

import ch.qos.logback.classic.Logger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryServiceFeign categoryServiceFeign;
	@Autowired
	private ExpenseServiceFeign expenseServiceFeign;
	@Autowired
	private BudgetServiceFeign budgetServiceFeign;
	@Autowired
	private JwtValidationService jwtValidationService;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity) {
		UserEntity ub = userService.createUser(userEntity);
		return new ResponseEntity<UserEntity>(ub, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Long userId, @RequestBody UserEntity user) {
		return new ResponseEntity<UserEntity>(userService.updateUser(userId, user), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
	    try {
	        UserEntity user = userService.getUser(userId);
	        return ResponseEntity.ok(user);
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching user data.");
	    }
	}


	@GetMapping("/profile")
	public ResponseEntity<?> getLoggedInUser(HttpServletRequest request) {
	    String token = request.getHeader("Authorization"); // Get token from header

	    if (token == null || !jwtValidationService.validateToken(token)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
	    }

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    // Check if the user is authenticated
	    if (authentication != null && authentication.isAuthenticated()) {
	        String username = authentication.getName(); // Fetch the username from JWT

	        // Find the user by email (username)
	        Optional<UserEntity> userOpt = userRepo.findByEmail(username);

	        if (userOpt.isPresent()) {
	            UserEntity user = userOpt.get();

	            // Fetch associated categories from Category Service
	            List<Category> categories = categoryServiceFeign.getCategoryByUserid(user.getUserId()).getBody();
	            List<Budget> budgets = budgetServiceFeign.getBudgetByUserid(user.getUserId()).getBody();
	            List<Expense> expenses = expenseServiceFeign.getExpensesByBudgetId(user.getUserId()).getBody();

	            // Create response including user details and categories
	            UserLogin userProfileResponse = new UserLogin(user, categories, budgets, expenses);
	            return ResponseEntity.ok(userProfileResponse);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
	    }
	}


	@GetMapping
	public ResponseEntity<List<UserEntity>> getAllUser() {
		return new ResponseEntity<List<UserEntity>>(userService.getAllUser(), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.noContent().build();
		} catch (NoUserFound e) {
			return ResponseEntity.notFound().build();

		}
	}
}
