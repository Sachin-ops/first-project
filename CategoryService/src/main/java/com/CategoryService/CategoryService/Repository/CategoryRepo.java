package com.CategoryService.CategoryService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CategoryService.CategoryService.Entity.CategoryEntity;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>{

    List<CategoryEntity> findByUserId(Long userId);
}
