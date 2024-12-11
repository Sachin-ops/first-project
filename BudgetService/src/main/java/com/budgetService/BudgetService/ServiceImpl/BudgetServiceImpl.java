package com.budgetService.BudgetService.ServiceImpl;

import java.util.List;

import java.util.Optional;
import java.util.logging.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.budgetService.BudgetService.Entity.BudgetEntity;
import com.budgetService.BudgetService.Exception.BudgetNotFound;
import com.budgetService.BudgetService.Exception.NoBudgetPresent;
import com.budgetService.BudgetService.Repository.BudgetRepo;
import com.budgetService.BudgetService.Service.BudgetService;

@Service
public class BudgetServiceImpl implements BudgetService{

	@Autowired
	private BudgetRepo repo;

	@Autowired
	private RestTemplate restTemplate;
	

	@Override
	public BudgetEntity createBudget(BudgetEntity budgetEntity) {
//	    if (budgetEntity.getUserId() == null) {
//	        throw new IllegalArgumentException("User ID cannot be null");
//	    }
	    return repo.save(budgetEntity);  // Make sure user_id is not null
	}
	
//	public BudgetEntity createBudget(BudgetEntity budgetEntity, Long userId) {
//	    UserEntity user = userRepository.findById(userId)
//	                                    .orElseThrow(() -> new UserNotFoundException("User not found"));
//	    budgetEntity.setUser(user);  // Set the user object, not just userId
//	    return repo.save(budgetEntity);
//	}


	@Override
	public BudgetEntity getBudget(Long id) {
	   BudgetEntity budget= repo.findById(id).orElseThrow(()-> new BudgetNotFound("No budget present!!"));
	  
	   
	   return  budget;
	   
	}


	

//	@Override
//	public Budget updateBudget(Long id, Budget budget) {
//		if (repo.existsById(id)) {
//			budget.setId(id);
//
//			return repo.save(budget);
//		}
//		return null;
//	}
	@Override
	public BudgetEntity updateBudget( Long id,  BudgetEntity budget)
	{
		return repo.findById(id).map(existingBudget ->{
			if(budget.getDescription()!=null)
			{
				existingBudget.setDescription(budget.getDescription());
			}
			if(budget.getName()!=null)
			{
				existingBudget.setName(budget.getName());
				
			}
			return repo.save(existingBudget);
		}).orElseThrow(()-> new BudgetNotFound("No budget to update"+id));
	}
		
		
		
		
		
	

//	@Override
//	public void deleteBudget(long id) {
//        if (!repo.existsById(id)) {
//            throw new NoBudgetPresent("No budget found with ID: " + id);
//        }
//        repo.deleteById(id);
//    }
	@Override
	public List<BudgetEntity> getAllBudget() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	@Override
	public void deleteBudget(Long id)  {
		if(!repo.existsById(id))
		{
			throw new NoBudgetPresent("No budget found with ID" + id);
			
		}
		repo.deleteById(id);
	}
	@Override
	public List<BudgetEntity> getBudgetByUserid(Long userId) {
	List<BudgetEntity> budget=	repo.getBudgetByUserId(userId);
	if(budget.isEmpty())
	{
		throw new NoBudgetPresent("No Budget Present!!");
	}
		return budget;
	}
	
}
