package com.seven.fitz.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.entity.Product;
import com.seven.fitz.entity.ProductImage;
import com.seven.fitz.repository.ProductImageRepository;
import com.seven.fitz.repository.ProductRepository;

@Service
public class AdminProductImageService {

    private final ProductImageRepository imageRepo;
    private final ProductRepository productRepo;
    private final AdminS3Service s3Service;

    public AdminProductImageService(ProductImageRepository imageRepo,
                               ProductRepository productRepo,AdminS3Service s3Service) {
        this.imageRepo = imageRepo;
        this.productRepo = productRepo;
        this.s3Service = s3Service;
    }

    public void uploadImage(Long productId, MultipartFile image){

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        imageRepo.findByProductId(productId)
                .ifPresent(imageRepo::delete);

        String imageUrl = s3Service.uploadProductImage(image);

        ProductImage pi = new ProductImage();
        pi.setProduct(product);
        pi.setImageUrl(imageUrl);
        pi.setPrimaryImage(true);

        imageRepo.save(pi);
    }
}
