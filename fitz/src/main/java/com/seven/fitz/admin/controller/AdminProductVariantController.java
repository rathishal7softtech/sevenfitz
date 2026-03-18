package com.seven.fitz.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.ProductVariantDTO;
import com.seven.fitz.admin.service.AdminProductVariantService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductVariantController {
	
	@Autowired
	private AdminProductVariantService variantservice;
	
// SAVE
    @PostMapping("/{productId}/variants")
    public ResponseEntity<?> saveVariants(
            @PathVariable Long productId,
            @RequestBody ProductVariantDTO dto,HttpSession session) {
        variantservice.saveVariants(productId,dto);
        return ResponseEntity.ok("Variants saved successfully");
    }
//  UPLOAD IMAGE
    @PostMapping(value="/variant/{variantId}/images",consumes = "multipart/form-data")
    public ResponseEntity<?> uploadVariantImages(
            @PathVariable Long variantId,
            @RequestPart("images") List<MultipartFile> images,HttpSession session){
        variantservice.uploadVariantImages(variantId, images);

        return ResponseEntity.ok("Images uploaded");
    }

//  REMOVE 
    @DeleteMapping("/variant/{variantId}")
    public ResponseEntity<?> removeVariant(@PathVariable Long variantId,HttpSession session) {

        variantservice.removeVariant(variantId);
        return ResponseEntity.ok("Variant removed successfully");
}
}
