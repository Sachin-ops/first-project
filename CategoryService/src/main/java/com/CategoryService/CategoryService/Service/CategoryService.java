package com.CategoryService.CategoryService.Service;

import java.util.List;

import com.CategoryService.CategoryService.Entity.CategoryEntity;

public interface CategoryService {
	List<CategoryEntity> getAllCategory();
	 CategoryEntity  addCategory(CategoryEntity category);
	 CategoryEntity updateCategory(Long id, CategoryEntity category);
	 void deleteCategory(Long id);
	 CategoryEntity getCategory(Long id);
	List<CategoryEntity>  getCategoryByUserid(Long id);
}
