package com.commerce.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.commerce.product.dto.ProductDTO;

public interface ProductService {

	ResponseEntity<ProductDTO> addProduct(ProductDTO productDTO);
	ResponseEntity<ProductDTO> getProductById(Long productID);
	ResponseEntity<List<ProductDTO>> listProduct();
	ResponseEntity<List<ProductDTO>> searchProducts(String search);
}
