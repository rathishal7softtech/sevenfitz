package com.seven.fitz.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.customer.DTO.ForgotPasswordRequest;
import com.seven.fitz.customer.DTO.LoginRequest;
import com.seven.fitz.customer.DTO.RegisterRequest;
import com.seven.fitz.customer.DTO.ResetPasswordRequest;
import com.seven.fitz.customer.DTO.VerifyOtpRequest;
import com.seven.fitz.customer.service.CustomerAuthService;
import com.seven.fitz.entity.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class CustomerAuthController {

    @Autowired
	private  CustomerAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){

        return ResponseEntity.ok(authService.register(request));

    }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request,
                        HttpSession session){

        User user = authService.login(request);

        session.setAttribute("USER_ID", user.getId());

        return "Customer login success";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "Logout successful";
    }
    
    @GetMapping("/profile")
    public User getProfile(HttpSession session){
        return authService.getLoggedUser(session);
    }
    
    // SEND OTP
    @PostMapping("/forgot-password")
    public ResponseEntity<?> sendOtp(@RequestBody ForgotPasswordRequest request){

    	return ResponseEntity.ok(authService.sendOtp(request.getEmail()));
    }

    // VERIFY OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request){

    	return ResponseEntity.ok(
                authService.verifyOtp(
                        request.getEmail(),
                        request.getOtp()
                )
        );
    } 

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest request){

        return ResponseEntity.ok(
                authService.resetPassword(
                        request.getEmail(),
                        request.getNewPassword()
                )
        );
    }
    

}
