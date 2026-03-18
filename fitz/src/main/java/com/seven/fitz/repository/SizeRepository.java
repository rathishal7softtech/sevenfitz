package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Size;

public interface SizeRepository extends JpaRepository<Size,Long>{
	
	boolean existsByNameIgnoreCase(String name);
	
	List<Size> findByNameContainingIgnoreCase(String name);
	
	Page<Size> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
