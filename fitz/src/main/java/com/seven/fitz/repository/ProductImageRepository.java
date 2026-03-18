package com.seven.fitz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long>{
	
	Optional<ProductImage> findByProductId(Long productId);

}
