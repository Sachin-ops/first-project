package com.CategoryService.CategoryService.ServiceImpl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.CategoryService.CategoryService.Entity.CategoryEntity;
import com.CategoryService.CategoryService.Exception.CategoryNotFoundException;
import com.CategoryService.CategoryService.Exception.ResourceNotFoundException;
import com.CategoryService.CategoryService.Repository.CategoryRepo;
import com.CategoryService.CategoryService.Service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(CategoryServiceimpl.class);
	
	@Override
	public List<CategoryEntity> getAllCategory() {
		
		return repo.findAll();
	}

	@Override
	public CategoryEntity addCategory(CategoryEntity category) {
		
	return repo.save(category);
	}

	@Override
	public CategoryEntity updateCategory(Long id, CategoryEntity category) {
		Optional<CategoryEntity> existingCategory= repo.findById(id);
		if(!existingCategory.isPresent())
		{
			throw new ResourceNotFoundException("Category is not found with ID"+ id);
		}
		else {
			CategoryEntity updatedCategory= existingCategory.get();
			updatedCategory.setName(category.getName());
			updatedCategory.setDescription(category.getDescription());
			return repo.save(updatedCategory);
		}
	}

	@Override
	public void deleteCategory(Long id) {
		repo.deleteById(id);
		
	}

	public CategoryEntity getCategory(Long id) {
	    Optional<CategoryEntity> getCategory = repo.findById(id);
	    if (getCategory.isPresent()) {
	        return getCategory.get();    
	    } else {
	        logger.error("No category found with ID: {}", id); // Using SLF4J for logging
	        throw new CategoryNotFoundException("Category with ID " + id + " not found");
	    }
	}


	@Override
	public List<CategoryEntity> getCategoryByUserid(Long userId) {
		List<CategoryEntity> category= repo.findByUserId(userId);
		if(category.isEmpty())
		{
			throw new ResourceNotFoundException("category is not present!!");
		}
		return category;
	}
	
}
