package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.ProductVariantImage;

public interface ProductVariantImageRepository extends JpaRepository<ProductVariantImage,Long>{
	
	 List<ProductVariantImage> findByVariantId(Long variantId);

	 int countByVariantId(Long variantId);
}
