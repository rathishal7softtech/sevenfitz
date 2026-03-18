package com.seven.fitz.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.admin.DTO.ProductCreateDTO;
import com.seven.fitz.admin.DTO.ProductDetailsDTO;
import com.seven.fitz.entity.Product;
import com.seven.fitz.entity.ProductAttribute;
import com.seven.fitz.repository.CategoryRepository;
import com.seven.fitz.repository.ProductAttributeRepository;
import com.seven.fitz.repository.ProductRepository;
import com.seven.fitz.repository.SubCategoryRepository;

@Service
public class AdminProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductAttributeRepository attributeRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private SubCategoryRepository subCategoryRepo;

//    add product
    public Product createProduct(ProductCreateDTO dto) {

        Product product = new Product();
        product.setProductCode("PRD-" + System.currentTimeMillis());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription( ));

        

        product.setCategory(
                categoryRepo.findById(dto.getCategoryId()).orElseThrow()
        );

        product.setSubCategory(
                subCategoryRepo.findById(dto.getSubCategoryId()).orElseThrow()
        );

        Product savedProduct = productRepo.save(product);

        // Save fixed attributes
        ProductDetailsDTO d = dto.getDetails();

        saveAttr(savedProduct, "Fabric", d.getFabric());
        saveAttr(savedProduct, "Pattern", d.getPattern());
        saveAttr(savedProduct, "Fit", d.getFit());
        saveAttr(savedProduct, "Neckline", d.getNeckline());
        saveAttr(savedProduct, "SKU", d.getSku());
        saveAttr(savedProduct, "Origin", d.getOrigin());

        return savedProduct;
    }
// method for saving details
    private void saveAttr(Product product, String key, String value) {
        if (value != null && !value.isBlank()) {
            ProductAttribute pa = new ProductAttribute();
            pa.setAttributeKey(key);
            pa.setAttributeValue(value);
            pa.setProduct(product);
            attributeRepo.save(pa);
        }
    }

}