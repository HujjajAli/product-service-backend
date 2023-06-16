package com.commerce.product.util.exception;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.commerce.product.dto.ApiError;
import com.commerce.product.util.exception.OfferNotValidException;

@ControllerAdvice
public class CustomeExceptionHandler {
	
	@ExceptionHandler({OfferNotValidException.class,InvalidCurrencyException.class})
	@ResponseBody
	public ResponseEntity<?> offerNotValidAndCurrencyNotValidHandler(Exception e,ServletWebRequest request){
		ApiError error = new ApiError();
		error.setPathUri(request.getDescription(true));
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setErrors(Arrays.asList(e.getMessage()));
		return new ResponseEntity(error,new HttpHeaders(),error.getStatus());
	}
	
}
