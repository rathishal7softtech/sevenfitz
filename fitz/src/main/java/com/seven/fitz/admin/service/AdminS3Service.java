package com.seven.fitz.admin.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class AdminS3Service {
	

	    private final S3Client s3Client;

	    @Value("${aws.s3.bucket}")
	    private String bucket;
	    
	    public AdminS3Service(S3Client s3Client) {
	        this.s3Client = s3Client;
	    }
	    // PRODUCT IMAGE
	    public String uploadProductImage(MultipartFile file) {
	        return upload(file, "products/");
	    }
	    // CATEGORY IMAGE
	    public String uploadCategoryImage(MultipartFile file) {
	        return upload(file, "categories/");
	    }

	    // SUB CATEGORY IMAGE
	    public String uploadSubCategoryImage(MultipartFile file) {
	        return upload(file, "subcategory/");
	    }
	    
        // OFFER IMAGE
	    public String uploadOfferImage(MultipartFile file) {
	        return upload(file, "offer/");
	    }
	    //BANNER
	    public String uploadBannerImage(MultipartFile file) {
	        return upload(file, "banner/");
	    }

	    // COMMON METHOD
	    public String upload(MultipartFile file, String folder) {

	        String key = folder + UUID.randomUUID() + "-" + file.getOriginalFilename();

	        try {
	            PutObjectRequest request = PutObjectRequest.builder()
	                    .bucket(bucket)
	                    .key(key)
	                    .contentType(file.getContentType())
	                    .build();

	            s3Client.putObject(
	                    request,
	                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
	            );

	            return "https://" + bucket + ".s3.amazonaws.com/" + key;

	        } catch (IOException e) {
	            throw new RuntimeException("Image upload failed", e);
	        }
	    }

}

	



