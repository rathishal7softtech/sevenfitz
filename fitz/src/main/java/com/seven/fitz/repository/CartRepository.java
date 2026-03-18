package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
	
	List<Cart> findByUserId(Long userId);

}
