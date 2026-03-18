package com.seven.fitz.customer.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.LoginRequest;
import com.seven.fitz.customer.DTO.RegisterRequest;
import com.seven.fitz.entity.PasswordResetOtp;
import com.seven.fitz.entity.User;
import com.seven.fitz.repository.PasswordResetOtpRepository;
import com.seven.fitz.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerAuthService {
    
	@Autowired
    private  UserRepository userRepository;
	@Autowired
    private  PasswordEncoder passwordEncoder;
	@Autowired
	private PasswordResetOtpRepository otpRepository;
	@Autowired
	private CustomerEmailService emailService;

    public String register(RegisterRequest request){

        // Terms check
        if(Boolean.FALSE.equals(request.getAgreeTerms())){
            throw new RuntimeException("You must accept Terms & Conditions");
        }

        // password match check
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }

        // email exists check
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "Account created successfully";
    }
    
    public User login(LoginRequest request){

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
    
    public User getLoggedUser(HttpSession session){

        Long userId = (Long) session.getAttribute("CUSTOMER_ID");

        if(userId == null){
            throw new RuntimeException("User not logged in");
        }

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public String sendOtp(String email){

        // old OTP delete
        otpRepository.deleteByEmail(email);

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        PasswordResetOtp reset = new PasswordResetOtp();
        reset.setEmail(email);
        reset.setOtp(otp);
        reset.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(reset);

        emailService.sendOtp(email, otp);

        return "OTP sent";
    }
    
    public String verifyOtp(String email, String otp){

        PasswordResetOtp reset = otpRepository
        		.findTopByEmailOrderByExpiryTimeDesc(email)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if(!reset.getOtp().equals(otp)){
            throw new RuntimeException("Invalid OTP");
        }

        if(reset.getExpiryTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("OTP expired");
        }

        return "OTP verified";
    }
    
    public String resetPassword(String email,String newPassword){

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        return "Password updated";
    }
}