package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
	
	 List<Product> findTop10ByNameContainingIgnoreCase(String keyword);
	 
	 List<Product> findByCategoryId(Long categoryId);

	 List<Product> findBySubCategoryId(Long subCategoryId);

}
