package com.seven.fitz.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.admin.DTO.ProductCreateDTO;
import com.seven.fitz.admin.service.AdminProductService;
import com.seven.fitz.entity.Product;

import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private AdminProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(
            @RequestBody ProductCreateDTO dto,HttpSession session) {
        Product product = productService.createProduct(dto);

        return ResponseEntity.ok(
                Map.of(
                        "productId", product.getId(),
                        "productCode", product.getProductCode()
                )
        );
    }
}