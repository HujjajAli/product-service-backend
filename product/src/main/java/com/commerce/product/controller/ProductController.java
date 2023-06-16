package com.commerce.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.product.ProductApplication;
import com.commerce.product.dto.ProductDTO;
import com.commerce.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/product")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
		log.info("Calling Product Add");
		return productService.addProduct(productDTO);
	}
	
	@GetMapping("/product-id/{productid}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("productid")Long productid){
		log.info("Calling Get Product By ID");
		return productService.getProductById(productid);
	}
	
	@GetMapping("/search/{search-term}")
	public ResponseEntity<List<ProductDTO>> getProductBySearchTerm(@PathVariable("search-term")String search){
		log.info("Calling Get Search Product By Term");
		return productService.searchProducts(search);
	}
	
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> listProduct(){
		log.info("Calling Get Products");
		ResponseEntity<List<ProductDTO>> responseEntity = productService.listProduct();
		//log.info("All Products : {}",responseEntity);
		return responseEntity;
	}
	

}
