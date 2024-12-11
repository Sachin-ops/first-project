package com.UserService.UserService.External.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.UserService.UserService.Entity.Category;

@FeignClient("CATEGORY-SERVICE")
public interface CategoryServiceFeign {
	
//    @GetMapping("/category/users/{userId}")
//    ResponseEntity<List<Category>> getCategoryByU(@PathVariable Long userId);
	@GetMapping("category/users/{userId}")
	public ResponseEntity<List<Category>>getCategoryByUserid(@PathVariable ("userId") Long userId);
	

    @GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id) ;
    

	@PostMapping()
	ResponseEntity<Category> createCategory(@RequestBody Category category);

	@PutMapping("/{categoryId}")
	ResponseEntity<Category> updateCategory(@PathVariable  Long categoryId, @RequestBody Category category);

@DeleteMapping("/{categoryId}")
	ResponseEntity<String> deleteCategory(@PathVariable  Long categoryId);
}
