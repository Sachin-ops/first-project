package com.CategoryService.CategoryService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CategoryService.CategoryService.Entity.CategoryEntity;
import com.CategoryService.CategoryService.Service.CategoryService;

@RestController
@RequestMapping(value="category")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	//post mapping to create a category
	@PostMapping
	public ResponseEntity<CategoryEntity> addCategory(@RequestBody CategoryEntity category)
	{
		return new ResponseEntity<CategoryEntity>(categoryService.addCategory(category),HttpStatus.CREATED);
	}
	//get category by  id
	@GetMapping("/{id}")
	public ResponseEntity<CategoryEntity> getCategory(@PathVariable Long id) {
	    CategoryEntity category = categoryService.getCategory(id);
	    if (category != null) {
	        return new ResponseEntity<>(category, HttpStatus.OK); // Return 200 OK if category is found
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if category not found
	    }
	}


	@PutMapping("/{id}")
	public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id,@RequestBody CategoryEntity category)
	{
		
		return new ResponseEntity<CategoryEntity>(categoryService.updateCategory(id, category),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
	{
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
	

@GetMapping
public ResponseEntity<List<CategoryEntity>> getAllCategory()
{
return new ResponseEntity<List<CategoryEntity>>(categoryService.getAllCategory(),HttpStatus.OK);	
}


//get category by userid
@GetMapping("/users/{userId}")
public ResponseEntity<List<CategoryEntity>>getCategoryByUserid(@PathVariable ("userId") Long userId)
{
	return new ResponseEntity<List<CategoryEntity>>(categoryService.getCategoryByUserid(userId),HttpStatus.OK);
}

}

