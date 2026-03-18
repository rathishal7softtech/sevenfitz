package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven.fitz.entity.Offer;
import com.seven.fitz.entity.OfferProduct;

public interface OfferProductRepository extends JpaRepository<OfferProduct,Long> {
	
	List<OfferProduct> findByOffer(Offer offer);
}
