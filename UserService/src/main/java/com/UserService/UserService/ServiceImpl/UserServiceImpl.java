package com.UserService.UserService.ServiceImpl;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.slf4j.Logger;

import com.UserService.UserService.Entity.Budget;
import com.UserService.UserService.Entity.Category;
import com.UserService.UserService.Entity.Expense;
import com.UserService.UserService.Entity.UserEntity;
import com.UserService.UserService.Exception.UserAlreadyPresent;
import com.UserService.UserService.Exception.UserNotFoundException;
import com.UserService.UserService.External.Feign.BudgetServiceFeign;
import com.UserService.UserService.External.Feign.CategoryServiceFeign;
import com.UserService.UserService.External.Feign.ExpenseServiceFeign;

import com.UserService.UserService.Repo.UserRepo;
import com.UserService.UserService.Service.UserService;

import feign.FeignException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BudgetServiceFeign budgetService;

	@Autowired
	private CategoryServiceFeign categoryService;

	@Autowired
	private ExpenseServiceFeign expense;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
//	@Override
//	public UserEntity getUser(long userId) {
//		// get user from database with the help of user repository
////        UserEntity user = userRepo.findById(userId)
////                .orElseThrow(() -> new UserNotFoundException("User with given ID " + userId + " not found!"));
//////      
//		Optional<UserEntity> userOptional = userRepo.findById(userId);
//		if (userOptional.isEmpty()) {
//			throw new UserNotFoundException("User with given Id not found" + userId);
//		} else {
//			UserEntity user = userOptional.get();
//
//			// fetch the category from userid
//			ResponseEntity<List<Category>> categoryOfUser = categoryService.getCategory(user.getUserId());
//
//			// get the object from each category
//			List<Category> categoryList = categoryOfUser.getBody().stream().map(category -> {
//				// fetch the budget details into category
////		Budget budgetObject = budgetService.getBudgetById(category.getBudgetId());
//				Budget budgetObject = budgetService.getBudget(category.getId());
////fetch the expense details into budget
//				List<Expense> expenses = expense.getExpensesByBudgetId(budgetObject.getExpenseId()).getBody();
//				// set the budget into category
//				category.setBudget(budgetObject);
//				return category;
//			}).collect(Collectors.toList());
//			// convert the listof category into array
////		Category[] categoryArray = categoryList.toArray(new Category[0]);
//		Category[]	categoryArray	=categoryList.toArray(new Category[0]);
//			user.setCategories(categoryArray);
//			return user;
//
//		}
//
//	}
	@Override
	public UserEntity getUser(long userId) {
	    // Fetch user from the database
	    UserEntity user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User with given ID " + userId + " not found!"));

	    // Fetch categories using Feign client
	    ResponseEntity<List<Category>> categoryResponse = categoryService.getCategoryByUserid(userId);
	    if (categoryResponse.getBody() == null || categoryResponse.getBody().isEmpty()) {
	    	logger.warn("No categories found for user ID: {}", user.getUserId());
	    	user.setCategories(new Category[0]); // Set empty categories if none found
	        return user;
	    }

	    // Fetch and enrich categories with budgets and expenses
	    List<Category> enrichedCategories = categoryResponse.getBody().stream().map(category -> {
	        // Fetch budget for each category
	        Budget budget = budgetService.getBudget(category.getId());
	        
	        // Fetch expenses for the budget
	        List<Expense> expenses = expense.getExpensesByBudgetId(budget.getId()).getBody();
	        
	        // Set budget and expenses to the category
	        budget.setExpenses(expenses); // Assuming Budget has an expenses field
	        category.setBudget(budget);
	        return category;
	    }).collect(Collectors.toList());

	    // Convert list to array and set it in the user
	    Category[] categoryArray = enrichedCategories.toArray(new Category[0]);
	    user.setCategories(categoryArray);

	    return user;
	}


//	@Override
//	public UserEntity getUser(long userId) {
//	    // Fetch the user from the repository
//	    Optional<UserEntity> userOptional = userRepo.findById(userId);
//	    if (userOptional.isEmpty()) {
//	        throw new UserNotFoundException("User with given ID " + userId + " not found!");
//	    }
//	    UserEntity user = userOptional.get();
//	    
//	    try {
//	        // Fetch categories for the user
//	        ResponseEntity<List<Category>> categoryOfUser = categoryService.getCategory(user.getUserId());
//	        
//	        if (categoryOfUser == null || categoryOfUser.getBody() == null) {
//	        	//logger.info("Categories fetched for userId {}: {}", user.getUserId(), categories);
//
//	            throw new RuntimeException("Failed to fetch categories for user ID: " + user.getUserId());
//	        }
//
//	        // Process categories
//	        List<Category> categoryList = categoryOfUser.getBody().stream().map(category -> {
//	            // Fetch budget for each category
//	            Budget budgetObject = budgetService.getBudget(category.getId());
//	            if (budgetObject == null) {
//	                throw new RuntimeException("Failed to fetch budget for category ID: " + category.getId());
//	            }
//
//	            // Fetch expenses for the budget
//	            ResponseEntity<List<Expense>> expensesResponse = expense.getExpensesByBudgetId(budgetObject.getExpenseId());
//	            if (expensesResponse == null || expensesResponse.getBody() == null) {
//	                throw new RuntimeException("Failed to fetch expenses for budget ID: " + budgetObject.getExpenseId());
//	            }
//
////	            List<Expense> expenses = expensesResponse.getBody();
////                 budgetObject.setExpense((Expense) expenses);
//	            List<Expense> expenses = expensesResponse.getBody();
//	            budgetObject.setExpenses(expenses);
//	            // Set budget in category
//	            category.setBudget(budgetObject);
//	            return category;
//	        }).collect(Collectors.toList());
//
//	        // Convert the list of categories to an array and set in user
//	        Category[] categoryArray = categoryList.toArray(new Category[0]);
//	        user.setCategories(categoryArray);
//
//	    } catch (Exception e) {
//	        logger.error("Error while fetching user data: {}", e.getMessage(), e);
//	        throw new RuntimeException("Internal server error while processing user ID: " + userId);
//	    }
//
//	    return user;
//	}
//	@Override
//	public UserEntity getUser(long userId) {
//	    Optional<UserEntity> userOptional = userRepo.findById(userId);
//	    if (userOptional.isEmpty()) {
//	        throw new UserNotFoundException("User with given ID " + userId + " not found!");
//	    }
//	    UserEntity user = userOptional.get();
//
//	    try {
//	        ResponseEntity<List<Category>> categoryResponse = categoryService.getCategoryByUserid(user.getUserId());
//	        List<Category> categoryList = categoryResponse.getBody();
//
//	        if (categoryList == null || categoryList.isEmpty()) {
//	            logger.warn("No categories found for userId {}", userId);
//	            user.setCategories(new Category[0]);
//	            return user;
//	        }
//
//	        List<Category> processedCategories = categoryList.stream().map(category -> {
//	            try {
//	                Long id = category.getBudgetId();
//	                if (id == null) {
//	                    throw new IllegalArgumentException("Category budgetId cannot be null for categoryId " + category.getUserId());
//	                }
//
//	                Budget budget = budgetService.getBudget(id);
//	                if (budget == null) {
//	                    throw new RuntimeException("Failed to fetch budget for budgetId " + id);
//	                }
//
//	                ResponseEntity<List<Expense>> expenseResponse = expense.getExpensesByBudgetId(budget.getExpenseId());
//	                List<Expense> expenses = expenseResponse.getBody();
//	                budget.setExpenses(expenses != null ? expenses : List.of());
//
//	                category.setBudget(budget);
//	                return category;
//
//	            } catch (FeignException.NotFound ex) {
//	                logger.error("Data not found for categoryId {}", category.getUserId());
//	                throw new RuntimeException("Data not found for categoryId " + category.getUserId(), ex);
//	            }
//	        }).collect(Collectors.toList());
//
//	        user.setCategories(processedCategories.toArray(new Category[0]));
//
//	    } catch (Exception e) {
//	        logger.error("Error while fetching user data: {}", e.getMessage(), e);
//	        throw new RuntimeException("Internal server error while processing user ID: " + userId, e);
//	    }
//
//	    return user;
//	}

	@Override
	public UserEntity updateUser(long userId, UserEntity userEntity) {
		return userRepo.findById(userId).map(existingUser -> {
			existingUser.setEmail(userEntity.getEmail());
			existingUser.setName(userEntity.getName());
			existingUser.setPwd(userEntity.getPwd());
			return userRepo.save(existingUser);
		}).orElseThrow(() -> new UserNotFoundException("User Not found with ID " + userId));
	}

	@Override
	public UserEntity createUser(UserEntity userEntity) {
//        Optional<UserEntity> existingUser = userRepo.findByEmail(userEntity.getEmail());
		Optional<UserEntity> existingUser = userRepo.findByEmail(userEntity.getEmail());
		if (existingUser.isPresent()) {
			throw new UserAlreadyPresent("User with email " + userEntity.getEmail() + " already exists!");
		}
		return userRepo.save(userEntity);
	}

	@Override
	public UserEntity deleteUser(long userId) {
		return userRepo.findById(userId).map(user -> {
			userRepo.deleteById(userId);
			return user;
		}).orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " is not present!"));
	}

	@Override
	public List<UserEntity> getAllUser() {
		List<UserEntity> users = userRepo.findAll();
		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found!");
		}
		return users;
	}

//	@Override
//	public UserEntity getUser(long userId) {
//		Optional<UserEntity> user1 = userRepo.findById(userId);
//		if (user1.isEmpty()) {
//
//			throw new UserNotFoundException("User with given Id is not presnent!!" + userId);
//		} else {
//			UserEntity user = user1.get();
//			categoryService.getCategoryByUserid(user.getCategories());
//			
//			
//			
//			return user;
//		}

	}


