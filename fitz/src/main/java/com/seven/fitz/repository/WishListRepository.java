package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.WishList;

public interface WishListRepository extends JpaRepository<WishList,Long>{
	
	List<WishList> findByUserId(Long userId);
	boolean existsByUserIdAndProductId(Long userId, Long productId);

}
