package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
	
	List<Order> findByUser_Id(Long userId);

}
