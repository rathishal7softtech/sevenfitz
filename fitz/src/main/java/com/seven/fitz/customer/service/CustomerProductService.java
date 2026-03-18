package com.seven.fitz.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.ProductResponse;
import com.seven.fitz.customer.DTO.ProductSizeResponse;
import com.seven.fitz.customer.DTO.ProductVariantResponse;
import com.seven.fitz.entity.Product;
import com.seven.fitz.entity.ProductVariant;
import com.seven.fitz.entity.ProductVariantImage;
import com.seven.fitz.repository.ProductRepository;

@Service
public class CustomerProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public ProductResponse getProduct(Long id){

	    Product product =repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    ProductResponse response = new ProductResponse();

	    response.setId(product.getId());
	    response.setName(product.getName());
	    response.setDescription(product.getDescription());
	    response.setBrand(product.getBrand());

	    response.setCategory(product.getCategory().getTitle());
	    response.setSubCategory(product.getSubCategory().getTitle());

	    List<ProductVariantResponse> variantResponses = new ArrayList<>();

	    for(ProductVariant variant : product.getVariants()){

	        ProductVariantResponse variantResponse = new ProductVariantResponse();

	        variantResponse.setColor(variant.getColor().getName());

	        List<String> images = variant.getImages()
	                .stream()
	                .map(ProductVariantImage::getImageUrl)
	                .toList();

	        variantResponse.setImages(images);

	        List<ProductSizeResponse> sizes = variant.getSizes()
	                .stream()
	                .map(size -> {
	                    ProductSizeResponse sr = new ProductSizeResponse();
	                    sr.setSize(size.getSize().getName());
	                    sr.setMrp(size.getMrp());
	                    sr.setStock(size.getStock());
	                    return sr;
	                })
	                .toList();

	        variantResponse.setSizes(sizes);

	        variantResponses.add(variantResponse);
	    }

	    response.setVariants(variantResponses);

	    return response;
	}
	
	public List<ProductResponse> getAllProducts(){

	    return repository.findAll()
	            .stream()
	            .map(this::convertToResponse)
	            .toList();
	}


	public List<ProductResponse> getByCategory(Long categoryId){

	    return repository.findByCategoryId(categoryId)
	            .stream()
	            .map(this::convertToResponse)
	            .toList();
	}


	public List<ProductResponse> getBySubCategory(Long subCategoryId){

	    return repository.findBySubCategoryId(subCategoryId)
	            .stream()
	            .map(this::convertToResponse)
	            .toList();
	}


	public List<ProductResponse> searchProducts(String keyword){

	    return repository.findTop10ByNameContainingIgnoreCase(keyword)
	            .stream()
	            .map(this::convertToResponse)
	            .toList();
	}
	
	private ProductResponse convertToResponse(Product product){

	    ProductResponse response = new ProductResponse();

	    response.setId(product.getId());
	    response.setName(product.getName());
	    response.setDescription(product.getDescription());
	    response.setBrand(product.getBrand());

	    response.setCategory(product.getCategory().getTitle());
	    response.setSubCategory(product.getSubCategory().getTitle());

	    return response;
	}

}
