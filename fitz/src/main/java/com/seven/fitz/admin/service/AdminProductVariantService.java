package com.seven.fitz.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.ColorVariantDTO;
import com.seven.fitz.admin.DTO.ProductVariantDTO;
import com.seven.fitz.admin.DTO.SizeDTO;
import com.seven.fitz.entity.Color;
import com.seven.fitz.entity.Product;
import com.seven.fitz.entity.ProductSize;
import com.seven.fitz.entity.ProductVariant;
import com.seven.fitz.entity.ProductVariantImage;
import com.seven.fitz.entity.Size;
import com.seven.fitz.repository.ColorRepository;
import com.seven.fitz.repository.ProductRepository;
import com.seven.fitz.repository.ProductVariantImageRepository;
import com.seven.fitz.repository.ProductVariantRepository;
import com.seven.fitz.repository.SizeRepository;
@Service
public class AdminProductVariantService {
	
	@Autowired
    private ProductRepository productRepo;

    @Autowired
    private ColorRepository colorRepo;
    
    @Autowired
    private SizeRepository sizeRepo;

    @Autowired
    private ProductVariantRepository variantRepo;
    @Autowired
    private ProductVariantImageRepository imageRepo;
    @Autowired
    private  AdminS3Service s3Service;

    public void saveVariants(Long productId,ProductVariantDTO dto) {
    	
    	

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        for (ColorVariantDTO vdto : dto.getVariants()) {

            Color color = colorRepo.findById(vdto.getColorVariantId())
                    .orElseThrow(() -> new RuntimeException("Color not found"));
            
            if (variantRepo.existsByProductIdAndColorId(productId, color.getId())) {
                throw new RuntimeException("Variant already exists for this color");
            }

            ProductVariant variant = new ProductVariant();
            variant.setProduct(product);
            variant.setColor(color);

            List<ProductSize> sizeList = new ArrayList<>();

            for (SizeDTO sdto : vdto.getSizes()) {
            	
            	Size size = sizeRepo.findById(sdto.getSizeId())
            	            .orElseThrow(() -> new RuntimeException("Size not found"));
            	
                ProductSize ps = new ProductSize();
                ps.setSize(size);
                ps.setStock(sdto.getStock());
                ps.setMrp(sdto.getMrp());
                ps.setVariant(variant);

                sizeList.add(ps);
            }

            variant.setSizes(sizeList);
            variantRepo.save(variant);
        }
        
    }
    
    
    public void uploadVariantImages(Long variantId, List<MultipartFile> images){

        ProductVariant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        int count = imageRepo.countByVariantId(variantId);

        if(count + images.size() > 4){
            throw new RuntimeException("Maximum 4 images allowed");
        }

        boolean primary = count == 0;

        for(MultipartFile file : images){

            String url = s3Service.uploadProductImage(file);

            ProductVariantImage img = new ProductVariantImage();
            img.setVariant(variant);
            img.setImageUrl(url);

            if(primary){
                img.setPrimaryImage(true);
                primary = false;
            }

            imageRepo.save(img);
        }
    }
    
//    delete
    public void removeVariant(Long variantId) {

        ProductVariant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        variantRepo.delete(variant);   
    }
}
