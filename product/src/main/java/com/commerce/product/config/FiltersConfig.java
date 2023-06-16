package com.commerce.product.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.commerce.product.filter.RequestResponseLogger;

@Configuration
public class FiltersConfig {
	
	@Bean
	FilterRegistrationBean<RequestResponseLogger> createLoggers(RequestResponseLogger requestResponseLogger){
		FilterRegistrationBean<RequestResponseLogger> registrationBean = new FilterRegistrationBean<>();
		
		registrationBean.setFilter(requestResponseLogger);
		//registrationBean.addUrlPatterns("/food/api/product/*");
		
		return registrationBean;
	}

}
