package com.seven.fitz.admin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.AddSubCategoryRequest;
import com.seven.fitz.admin.DTO.AddSubCategoryResponse;
import com.seven.fitz.admin.DTO.EditSubCategoryResponse;
import com.seven.fitz.admin.DTO.ListSubCategoryResponse;
import com.seven.fitz.admin.DTO.SubcategoryDropdownResponse;
import com.seven.fitz.admin.DTO.ViewSubCategoryResponse;
import com.seven.fitz.entity.Category;
import com.seven.fitz.entity.SubCategory;
import com.seven.fitz.repository.CategoryRepository;
import com.seven.fitz.repository.SubCategoryRepository;

@Service
public class AdminSubCategoryService {
	
	 private final SubCategoryRepository repository;
	 private final CategoryRepository categoryRepository;
	 private final AdminS3Service s3Service;

	 public AdminSubCategoryService(SubCategoryRepository repository,
	                              CategoryRepository categoryRepository,
	                              AdminS3Service s3Service) {
	     this.repository = repository;
	     this.categoryRepository = categoryRepository;
	     this.s3Service = s3Service;
	    }

	
//	Drop down 
	 
	public List<SubcategoryDropdownResponse> getDropdown() {
	    return repository.findAll()
	            .stream()
	            .map(c -> new SubcategoryDropdownResponse(c.getId(), c.getTitle()))
	            .toList();
	}
	
	
//	Add Sub category
	public AddSubCategoryResponse create(AddSubCategoryRequest request, MultipartFile image) {

	    Category category = categoryRepository.findById(request.getCategoryId())
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	    SubCategory sub = new SubCategory();
	    sub.setTitle(request.getTitle());
	    sub.setDescription(request.getDescription());
	    sub.setCategory(category);

	    if (image != null) {
	        sub.setImageUrl(s3Service.uploadSubCategoryImage(image));
	    }

	    SubCategory saved = repository.save(sub);

	    return new AddSubCategoryResponse(
	            saved.getId(),
	            saved.getTitle(),
	            category.getTitle(),
	            saved.getImageUrl()
	    );
	    
	}
	
//	List of SubCategory
	public Page<ListSubCategoryResponse> list(String search,LocalDate startDate,
	        LocalDate endDate, Pageable pageable) {

	    Page<SubCategory> page;

	    // Convert LocalDate → LocalDateTime
	    LocalDateTime start = (startDate != null)
	            ? startDate.atStartOfDay()
	            : null;

	    LocalDateTime end = (endDate != null)
	            ? endDate.atTime(23, 59, 59)
	            : null;

	    if (search != null && start != null && end != null) {
	        page = repository.findByTitleContainingIgnoreCaseAndCreatedAtBetween(
	                search, start, end, pageable
	        );
	    } 
	    else if (search != null) {
	        page = repository.findByTitleContainingIgnoreCase(search, pageable);
	    } 
	    else if (start != null && end != null) {
	        page = repository.findByCreatedAtBetween(start, end, pageable);
	    } 
	    else {
	        page = repository.findAll(pageable);
	    }

	    return page.map(sc -> new ListSubCategoryResponse(
	            sc.getId(),
	            sc.getTitle(),
	            sc.getCategory() != null
                ? sc.getCategory().getTitle()
                : "No Category Assigned"
	    ));

	}
	
//	view
	public ViewSubCategoryResponse getById(Long id) {

	    SubCategory sc = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Sub Category not found"));

	    String categoryName = (sc.getCategory() != null)
                ? sc.getCategory().getTitle()
                : "No Category Assigned";

        String description = (sc.getDescription() != null && !sc.getDescription().isBlank())
                ? sc.getDescription()
                : "No description available";

        return new ViewSubCategoryResponse(
                sc.getId(),
                sc.getTitle(),
                categoryName,
                description
        );
	}
	
//delete
	public void delete(Long id) {
	    repository.deleteById(id);
	}
	
//Edit
	public EditSubCategoryResponse update(Long id,
	        AddSubCategoryRequest request,
	        MultipartFile image) {

	    SubCategory sub = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Sub Category not found"));

	    Category category = categoryRepository.findById(request.getCategoryId())
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	    sub.setTitle(request.getTitle());
	    sub.setDescription(request.getDescription());
	    sub.setCategory(category);

	    if (image != null) {
	        sub.setImageUrl(s3Service.uploadSubCategoryImage(image));
	    }

	    SubCategory updated = repository.save(sub);

	    return new EditSubCategoryResponse(
	            updated.getId(),
	            updated.getTitle(),
	            category.getTitle(),
	            updated.getImageUrl()
	    );
	}	
}
