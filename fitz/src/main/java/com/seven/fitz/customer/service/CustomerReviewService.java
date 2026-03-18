package com.seven.fitz.customer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.ReviewRequest;
import com.seven.fitz.entity.Product;
import com.seven.fitz.entity.ProductReview;
import com.seven.fitz.entity.ReviewImage;
import com.seven.fitz.entity.User;
import com.seven.fitz.repository.ProductRepository;
import com.seven.fitz.repository.ProductReviewRepository;
import com.seven.fitz.repository.UserRepository;

@Service
public class CustomerReviewService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public String addReview(Long userId, ReviewRequest request){

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProductReview review = new ProductReview();

        review.setProduct(product);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now());

        List<ReviewImage> images = new ArrayList<>();

        if(request.getImages()!=null){
        	
        	if(request.getImages().length > 3){
                throw new RuntimeException("Maximum 3 images allowed");
            }

            for(var file : request.getImages()){

                ReviewImage img = new ReviewImage();

                img.setImageUrl(file.getOriginalFilename());
                img.setReview(review);

                images.add(img);
            }
        }

        review.setImages(images);

        reviewRepository.save(review);

        return "Review added";
    }
}