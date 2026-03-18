package com.seven.fitz.admin.service;

import java.io.IOException;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.BannerRequest;
import com.seven.fitz.entity.Banner;
import com.seven.fitz.repository.BannerRepository;

@Service
public class AdminBannerService {

    private final BannerRepository bannerRepository;
    private final AdminS3Service s3Service;

    public AdminBannerService(BannerRepository bannerRepository,
                         AdminS3Service s3Service) {
        this.bannerRepository = bannerRepository;
        this.s3Service = s3Service;
    }

    public Banner create(BannerRequest request, MultipartFile image) throws IOException {

        String imageUrl = s3Service.uploadOfferImage(image);

        Banner banner = new Banner();
        banner.setTitle(request.getTitle());
        banner.setSubtitle(request.getSubtitle());
        banner.setImageUrl(imageUrl);

        return bannerRepository.save(banner);
    }

    public Page<Banner> getBanners(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bannerRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }

    public Banner toggleStatus(Long id) {

        Banner banner = bannerRepository.findById(id).orElseThrow();

        banner.setActive(!banner.getActive());

        return bannerRepository.save(banner);
    }
}
