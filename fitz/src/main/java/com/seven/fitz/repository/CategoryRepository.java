package com.seven.fitz.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Page<Category> findAllByActiveTrue(Pageable pageable);

	Page<Category> findByTitleContainingIgnoreCase(String search,Pageable pageable);

}
