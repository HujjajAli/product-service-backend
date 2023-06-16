package com.commerce.product.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {

	
	private String pathUri;
	private HttpStatus status;
	private List<String> errors;
	
}
