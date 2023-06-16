package com.commerce.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.commerce.product.orm.Category;

public interface CategoryRepository extends RevisionRepository<Category, Long, Long>,JpaRepository<Category, Long> {

}
