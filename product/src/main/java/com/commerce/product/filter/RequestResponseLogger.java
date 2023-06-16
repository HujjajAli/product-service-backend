package com.commerce.product.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commerce.product.dto.CategoryDTO;
import com.commerce.product.filter.util.MyDelegatingServletInputStream;
import com.commerce.product.filter.util.MyDelegatingServletOutputStream;
import com.commerce.product.util.UtilConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
//@Order(1)
public class RequestResponseLogger implements Filter{
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		MyCustomeHttpRequestWrapper  requestWrapper  = new MyCustomeHttpRequestWrapper((HttpServletRequest)request);
		String requestURI = requestWrapper.getRequestURI();
		log.info("Request URI:  - {}",requestURI);
		log.info("Request Method:  - {}",requestWrapper.getMethod());
		String requestData = new String(requestWrapper.getByteArray());
		String uriTest = UtilConstants.BASE_APP_URI+"category/test";
		if(requestURI.equalsIgnoreCase(UtilConstants.BASE_APP_URI+"category/test")) {
			CategoryDTO catRequest = objectMapper.readValue(requestData, CategoryDTO.class);
			catRequest.setBrand("*****");
			requestData = objectMapper.writeValueAsString(catRequest);
			log.info("Request Body:  - {}",requestData);
		}else {
			log.info("Request Body:  - {}",requestData);
		}
		
		
		
		MyCustomeHttpResponseWrapper  responseWrapper  = new MyCustomeHttpResponseWrapper((HttpServletResponse)response);
		
		chain.doFilter(requestWrapper, responseWrapper);
		
		log.info("Respose Status:  - {}",responseWrapper.getStatus());
		log.info("Response Body:  - {}",new String(responseWrapper.getBaos().toByteArray()));
		
	}
	
	@Getter
	private class MyCustomeHttpRequestWrapper extends HttpServletRequestWrapper{
		private byte[] byteArray;
		public MyCustomeHttpRequestWrapper(HttpServletRequest request) {
			super(request);
			try {
				byteArray = IOUtils.toByteArray(request.getInputStream());
			}catch (Exception e) {
				throw new RuntimeException("error while reading request stream");
			}
		}
		@Override
		public ServletInputStream getInputStream() throws IOException {
			return new MyDelegatingServletInputStream(new ByteArrayInputStream(this.getByteArray()));
		}
	} 
	
	@Getter
	private class MyCustomeHttpResponseWrapper extends HttpServletResponseWrapper{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream  = new PrintStream(baos);
		
		public MyCustomeHttpResponseWrapper(HttpServletResponse response) {
			super(response);
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return new MyDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), printStream));
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			return super.getWriter();
		}
		
		
	}

}
