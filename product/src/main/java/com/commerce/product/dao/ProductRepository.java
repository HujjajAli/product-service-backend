package com.commerce.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.commerce.product.orm.Product;

public interface ProductRepository extends RevisionRepository<Product, Long, Long>,JpaRepository<Product, Long> {

}
