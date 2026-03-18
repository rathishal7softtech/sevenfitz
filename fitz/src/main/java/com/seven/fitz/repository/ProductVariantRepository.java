package com.seven.fitz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long>{
	
	boolean existsByProductIdAndColorId(Long productId, Long colorId);

}
