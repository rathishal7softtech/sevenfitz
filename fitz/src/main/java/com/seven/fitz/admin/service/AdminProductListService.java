package com.seven.fitz.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.seven.fitz.admin.DTO.ProductListResponse;
import com.seven.fitz.entity.Product;
import com.seven.fitz.repository.ProductListRepository;

@Service
public class AdminProductListService {
	
	    @Autowired
	    private ProductListRepository productRepository;
	    

	    public Page<ProductListResponse> getProductList(
	            String search, int page, int size) {

	        Pageable pageable =
	            PageRequest.of(page, size, Sort.by("id").descending());

	        Page<Product> products =
	            productRepository.searchProducts(search, pageable);

	        return products.map(p -> {
	            ProductListResponse dto = new ProductListResponse();
	            dto.setId(p.getId());
	            dto.setName(p.getName());
	            dto.setCategory(
	                p.getCategory() != null ? p.getCategory().getTitle() : null
	            );
	            dto.setSubCategory(
	                p.getSubCategory() != null ? p.getSubCategory().getTitle() : null
	            );
	            dto.setBrand(
	                p.getBrand() != null ? p.getBrand() : null
	            );
	            dto.setImage(
	            	    p.getImages() != null && !p.getImages().isEmpty()
	            	        ? p.getImages().get(0).getImageUrl()
	            	        : null
	            	);
	            return dto;
	        });
	    }

}
