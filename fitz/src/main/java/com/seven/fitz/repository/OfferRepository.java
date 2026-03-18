package com.seven.fitz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer,Long>{
	
	
	Page<Offer> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
