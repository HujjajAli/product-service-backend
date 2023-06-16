package com.commerce.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.commerce.product.dto.CategoryDTO;

public interface CategoryService {

	
	ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO);
	ResponseEntity<CategoryDTO> updateCategory(CategoryDTO categoryDTO);
	ResponseEntity<List<CategoryDTO>> getAllCategories();
}
