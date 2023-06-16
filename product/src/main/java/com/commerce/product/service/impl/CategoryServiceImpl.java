package com.commerce.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.commerce.product.dao.CategoryRepository;
import com.commerce.product.dto.CategoryDTO;
import com.commerce.product.orm.Category;
import com.commerce.product.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryDAO;

	@Override
	public ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO) {
		ResponseEntity<CategoryDTO> response = null;
		try {
			
			Category category = new Category();
			BeanUtils.copyProperties(categoryDTO, category);
			
			
			//category.setCreateDate(new Date().to);		
			categoryDAO.save(category);
			categoryDTO.setCid(category.getCid());
			response = ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
			log.info("Category Added Succesfully");
		}catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		return response;
	}

	@Override
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		ResponseEntity<List<CategoryDTO>> response = null;
		try {
			List<Category> allCategories = categoryDAO.findAll();
			List<CategoryDTO> allData = new ArrayList<CategoryDTO>();
			for(Category category:allCategories) {
				CategoryDTO categoryDTO = new CategoryDTO();
				BeanUtils.copyProperties(category, categoryDTO);
				allData.add(categoryDTO);
			}
			response = ResponseEntity.status(HttpStatus.CREATED).body(allData);
		}catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return response;
	}

	@Override
	public ResponseEntity<CategoryDTO> updateCategory(CategoryDTO categoryDTO) {
		ResponseEntity<CategoryDTO> response = null;
		try {
			
			Category category = categoryDAO.getById(categoryDTO.getCid());
			BeanUtils.copyProperties(categoryDTO, category);
			
			

			
			categoryDAO.save(category);
			categoryDTO.setCid(category.getCid());
			response = ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
			log.info("Category Updated Succesfully");
		}catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		return response;
	}

}
