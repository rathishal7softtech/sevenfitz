package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Color;

public interface ColorRepository extends JpaRepository<Color,Long>{
	
	boolean existsByNameIgnoreCase(String name);
	
	List<Color> findByNameContainingIgnoreCase(String keyword);

}
