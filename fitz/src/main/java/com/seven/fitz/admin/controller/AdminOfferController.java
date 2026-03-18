package com.seven.fitz.admin.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.OfferRequest;
import com.seven.fitz.admin.DTO.OfferResponse;
import com.seven.fitz.admin.service.AdminOfferService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/offers")
public class AdminOfferController {
	
	@Autowired
	private AdminOfferService offerService;

	@PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createOffer(

    		@RequestPart("title") String title,
            @RequestPart("subTitle") String subTitle,
            @RequestPart("offerPercentage") Integer offerPercentage,
            @RequestPart("description") String description,
            @RequestPart("offerClosedDate") String offerClosedDate,
            @RequestPart("image") MultipartFile image,
            HttpSession session) {

        OfferRequest request = new OfferRequest();
        request.setTitle(title);
        request.setSubTitle(subTitle);
        request.setOfferPercentage(offerPercentage);
        request.setDescription(description);
        request.setOfferClosedDate(LocalDate.parse(offerClosedDate));

        return ResponseEntity.ok(offerService.create(request, image));
    }
	
    @GetMapping("/list")
	public Page<OfferResponse> list(HttpSession session) {
	    Pageable pageable = PageRequest.of(0,10);
        return offerService.list(pageable);
	 }
}
