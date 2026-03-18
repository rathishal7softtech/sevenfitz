package com.seven.fitz.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.admin.DTO.ProductListResponse;
import com.seven.fitz.admin.service.AdminProductListService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/admin/product_list")
public class AdminProductListController {
	
	@Autowired
	private AdminProductListService service;
	
	@GetMapping("/page")
    public ResponseEntity<Page<ProductListResponse>> getProducts(
            @RequestPart(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,HttpSession session) {
	
        return ResponseEntity.ok(
            service.getProductList(search, page, size)
        );
    }
}
