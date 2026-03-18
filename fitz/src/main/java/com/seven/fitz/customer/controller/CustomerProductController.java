package com.seven.fitz.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.customer.DTO.ProductResponse;
import com.seven.fitz.customer.service.CustomerProductService;

@RestController
@RequestMapping("/api/products/customer")
public class CustomerProductController {
	
	@Autowired
	private CustomerProductService productService;
	
	@GetMapping("/get/{id}")
	public ProductResponse getProduct(@PathVariable Long id){

	    return productService.getProduct(id);
	}
	
	// 1️⃣ All products
    @GetMapping("/getall")
    public List<ProductResponse> getAllProducts(){

        return productService.getAllProducts();
    }


    // Category filter
    @GetMapping("/category/{id}")
    public List<ProductResponse> getByCategory(@PathVariable Long id){

        return productService.getByCategory(id);
    }


    // filter
    @GetMapping("/subcategory/{id}")
    public List<ProductResponse> getBySubCategory(@PathVariable Long id){

        return productService.getBySubCategory(id);
    }


    // Search
    @GetMapping("/search")
    public List<ProductResponse> searchProducts(@RequestParam String keyword){

        return productService.searchProducts(keyword);
    }


}
