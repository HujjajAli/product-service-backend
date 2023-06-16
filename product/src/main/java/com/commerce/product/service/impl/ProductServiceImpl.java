package com.commerce.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.commerce.product.dao.CategoryRepository;
import com.commerce.product.dao.ProductRepository;
import com.commerce.product.dto.CategoryDTO;
import com.commerce.product.dto.OriginDTO;
import com.commerce.product.dto.ProductDTO;
import com.commerce.product.orm.Category;
import com.commerce.product.orm.Product;
import com.commerce.product.service.ProductService;
import com.commerce.product.service.config.ProductConfiguration;
import com.commerce.product.util.MessageConstant;
import com.commerce.product.util.exception.InvalidCurrencyException;
import com.commerce.product.util.exception.OfferNotValidException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productDAO; 
	@Autowired
	private CategoryRepository categoryDAO;
	@Autowired(required = true)
	private ProductConfiguration productConfiguration;

	@Override
	public ResponseEntity<ProductDTO> addProduct(ProductDTO productDTO) {
		ResponseEntity<ProductDTO> response = null;
		if(productDTO.getPrice() == 0 && productDTO.getDiscount() > 0) {
			throw new OfferNotValidException(MessageConstant.OFFER_NOT_ALLOWED_MESSAGE);
		}
		
		if(!productConfiguration.getCurrencies().contains(productDTO.getCurrency().toUpperCase())) {
			throw new InvalidCurrencyException("Currency Not Allowed - Valid Currencies:"+productConfiguration.getCurrencies());
		}
		try {

			Product product = new Product();
			BeanUtils.copyProperties(productDTO, product);
			Long categoryId = productDTO.getCategory().getCid();
			Category category = categoryDAO.getById(categoryId);
			product.setCategory(category);
			productDAO.save(product);
			productDTO.setPid(product.getPid());
			response = ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
			log.info("Product Added Successfully");
		}catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		return response;
	}

	@Override
	public ResponseEntity<ProductDTO> getProductById(Long productID) {
		ResponseEntity<ProductDTO> response = null;

		try {
			Product product = productDAO.getById(productID);
			if(product != null && product.getPid() != null) {
				ProductDTO productDTO = new ProductDTO();
				BeanUtils.copyProperties(product, productDTO);
				Category category = product.getCategory();
				CategoryDTO categoryDTO = new CategoryDTO();
				BeanUtils.copyProperties(category, categoryDTO);
				productDTO.setCategory(categoryDTO);
				response = ResponseEntity.status(HttpStatus.OK).body(productDTO);
			}else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		return response;
	}

	@Override
	public ResponseEntity<List<ProductDTO>> listProduct() {
		ResponseEntity<List<ProductDTO>> response = null;
		try {
			List<Product> productsData = productDAO.findAll();
			List<ProductDTO> listProducts = new ArrayList<ProductDTO>();
			for(Product product:productsData) {
				ProductDTO productDTO = new ProductDTO();
				BeanUtils.copyProperties(product, productDTO);
				Category category = product.getCategory();
				CategoryDTO categoryDTO = new CategoryDTO();
				BeanUtils.copyProperties(category, categoryDTO);
				productDTO.setCategory(categoryDTO);
				
				OriginDTO oDTO1 = new OriginDTO(1L, "Pakistan");
				OriginDTO oDTO2 = new OriginDTO(2L, "Itay");
				
				List<OriginDTO> odLst= new ArrayList<OriginDTO>();
				odLst.add(oDTO1);
				odLst.add(oDTO2);
				
				productDTO.setOrigins(odLst);				
				listProducts.add(productDTO);
			}

			response = ResponseEntity.status(HttpStatus.OK).body(listProducts);
		}catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return response;
	}

	@Override
	public ResponseEntity<List<ProductDTO>> searchProducts(String search) {
		ResponseEntity<List<ProductDTO>> response = null;
		try {
			List<Product> productsData = productDAO.findAll();
			List<ProductDTO> listProducts = new ArrayList<ProductDTO>();
			for(Product product:productsData) {
				ProductDTO productDTO = new ProductDTO();
				BeanUtils.copyProperties(product, productDTO);
				Category category = product.getCategory();
				CategoryDTO categoryDTO = new CategoryDTO();
				BeanUtils.copyProperties(category, categoryDTO);
				productDTO.setCategory(categoryDTO);
				
				OriginDTO oDTO1 = new OriginDTO(1L, "Pakistan");
				OriginDTO oDTO2 = new OriginDTO(2L, "Itay");
				
				List<OriginDTO> odLst= new ArrayList<OriginDTO>();
				odLst.add(oDTO1);
				odLst.add(oDTO2);
				
				productDTO.setOrigins(odLst);				
				listProducts.add(productDTO);
			}
			listProducts = listProducts.stream().
				    filter(p -> p.getPname().equalsIgnoreCase(search)).
				    collect(Collectors.toList());
			

			response = ResponseEntity.status(HttpStatus.OK).body(listProducts);
		}catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return response;
	}

}
