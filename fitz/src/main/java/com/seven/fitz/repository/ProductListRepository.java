package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seven.fitz.entity.Product;

public interface ProductListRepository extends JpaRepository<Product,Long> {
	
	@Query("""
	        SELECT p FROM Product p
	        WHERE (:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')))
	    """)
	Page<Product> searchProducts(@Param("search") String search, Pageable pageable);
	
	@Query("""
	        SELECT 
	        p.id,
	        p.name,
	        c.title,
	        sc.title,
	        p.brand,
	        pi.imageUrl
	        FROM Product p
	        LEFT JOIN p.category c
	        LEFT JOIN p.subCategory sc
	        LEFT JOIN p.images pi
	        """)
	 List<Object[]> getProductList();

}
