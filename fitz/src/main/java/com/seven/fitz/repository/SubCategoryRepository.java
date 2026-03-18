package com.seven.fitz.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long>{
	
	Page<SubCategory> findByCreatedAtBetween(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );

    Page<SubCategory> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );

    Page<SubCategory> findByTitleContainingIgnoreCaseAndCreatedAtBetween(
            String title,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );

}
