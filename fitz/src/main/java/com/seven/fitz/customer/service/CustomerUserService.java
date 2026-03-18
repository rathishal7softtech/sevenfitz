package com.seven.fitz.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.UpdateProfileRequest;
import com.seven.fitz.customer.DTO.UserProfileResponse;
import com.seven.fitz.entity.User;
import com.seven.fitz.repository.UserRepository;

@Service
public class CustomerUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserProfileResponse getProfile(Long userId){

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    UserProfileResponse response = new UserProfileResponse();

	    response.setFullName(user.getFullName());
	    response.setEmail(user.getEmail());

	    return response;
	}
	
	public String updateProfile(Long userId, UpdateProfileRequest request){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(request.getFullName());

        userRepository.save(user);

        return "Profile updated successfully";
    }
}
