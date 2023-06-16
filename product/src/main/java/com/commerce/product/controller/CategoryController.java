package com.commerce.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.product.dto.CategoryDTO;
import com.commerce.product.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/category")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
		log.info("Calling Category Add");
		return categoryService.addCategory(categoryDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		log.info("Calling Category Add");
		return categoryService.getAllCategories();
	}
	
	@PutMapping
	public ResponseEntity<CategoryDTO> addCategoryTest(@RequestBody CategoryDTO categoryDTO){
		log.info("Calling Category Update");
		return categoryService.updateCategory(categoryDTO);
	}
	
	
}
