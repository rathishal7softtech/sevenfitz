package com.seven.fitz.admin.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.BannerRequest;
import com.seven.fitz.admin.DTO.IdRequest;
import com.seven.fitz.admin.service.AdminBannerService;
import com.seven.fitz.entity.Banner;

@RestController
@RequestMapping("/api/admin/banners")
public class AdminBannerController {
	
	private final AdminBannerService bannerService;

    public AdminBannerController(AdminBannerService bannerService) {
        this.bannerService = bannerService;
    }
    @PostMapping("/add")
    public Banner createBanner(
            @RequestPart BannerRequest request,
            @RequestPart MultipartFile image) throws IOException {

        return bannerService.create(request, image);
    }

    @PostMapping("/delete")
    public String deleteBanner(@RequestBody IdRequest request) {

        bannerService.delete(request.getId());

        return "Banner deleted";
    }
    @PostMapping("/status")
    public Banner toggleStatus(@RequestBody IdRequest request) {

        return bannerService.toggleStatus(request.getId());
    }
}