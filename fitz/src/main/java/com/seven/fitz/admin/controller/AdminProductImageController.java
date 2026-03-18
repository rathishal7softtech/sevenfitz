package com.seven.fitz.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.service.AdminProductImageService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/admin/addproducts")
public class AdminProductImageController {
	
   @Autowired	
   private AdminProductImageService imageService;

    @PostMapping(value="/{productId}/image",consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@PathVariable Long productId,@RequestPart MultipartFile image,HttpSession session){
        imageService.uploadImage(productId, image);

        return ResponseEntity.ok("Image uploaded");
    }
}
