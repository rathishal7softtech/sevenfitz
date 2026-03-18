package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview,Long>{
	
	List<ProductReview> findByProduct_Id(Long productId);

}
