package com.seven.fitz.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.OfferProductSearchResponse;
import com.seven.fitz.admin.DTO.OfferRequest;
import com.seven.fitz.admin.DTO.OfferResponse;
import com.seven.fitz.entity.Offer;
import com.seven.fitz.repository.OfferRepository;
import com.seven.fitz.repository.ProductRepository;

@Service
public class AdminOfferService {
	
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AdminS3Service s3Service;

	public OfferResponse create(OfferRequest request, MultipartFile image) {

		String imageUrl = s3Service.uploadOfferImage(image);
	    
	    Offer offer = new Offer();
	    offer.setTitle(request.getTitle());
        offer.setSubTitle(request.getSubTitle());
        offer.setOfferPercentage(request.getOfferPercentage());
        offer.setDescription(request.getDescription());
        offer.setOfferClosedDate(request.getOfferClosedDate());
        offer.setImageUrl(imageUrl);
        
	    offerRepository.save(offer);

	    OfferResponse response = new OfferResponse();
	    response.setId(offer.getId());
	    response.setTitle(offer.getTitle());
	    response.setSubTitle(offer.getSubTitle());
	    response.setOfferPercentage(offer.getOfferPercentage());
	    response.setDescription(offer.getDescription());
	    response.setOfferClosedDate(offer.getOfferClosedDate());

	    return response;
	}

	    public Page<OfferResponse> list(Pageable pageable) {

	        return offerRepository.findAllByOrderByCreatedAtDesc(pageable)
	                .map(this::map);
	    }

	    private OfferResponse map(Offer offer) {

	        OfferResponse res = new OfferResponse();

	        res.setId(offer.getId());
	        res.setTitle(offer.getTitle());
	        res.setSubTitle(offer.getSubTitle());
	        res.setOfferPercentage(offer.getOfferPercentage());
	        res.setDescription(offer.getDescription());
	        res.setOfferClosedDate(offer.getOfferClosedDate());
	        res.setExpired(offer.getExpired());

	        return res;
	    }
	    
	    public List<OfferProductSearchResponse> searchProducts(String keyword){

	        return productRepository
	                .findTop10ByNameContainingIgnoreCase(keyword)
	                .stream()
	                .map(p -> new OfferProductSearchResponse(
	                        p.getId(),
	                        p.getName(),
	                        p.getDescription()))
	                .toList();
	    }


}



