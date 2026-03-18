package com.seven.fitz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Banner;

public interface BannerRepository extends JpaRepository<Banner,Long>{
	
	Page<Banner> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
