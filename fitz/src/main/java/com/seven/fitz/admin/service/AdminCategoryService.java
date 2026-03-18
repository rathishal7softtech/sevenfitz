package com.seven.fitz.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.AddCategoryRequest;
import com.seven.fitz.admin.DTO.AddCategoryResponse;
import com.seven.fitz.admin.DTO.EditCategoryResponse;
import com.seven.fitz.admin.DTO.ListCategoryResponse;
import com.seven.fitz.admin.DTO.ViewCategoryResponse;
import com.seven.fitz.entity.Category;
import com.seven.fitz.repository.CategoryRepository;

@Service

public class AdminCategoryService {

    private final CategoryRepository repository;
    private final AdminS3Service s3Service;
    
    public AdminCategoryService(CategoryRepository repository,
            AdminS3Service s3Service) {
    	this.repository = repository;
    	this.s3Service = s3Service;
    	
    }
//    Add
    public AddCategoryResponse create(AddCategoryRequest request, MultipartFile image) {
    	if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new RuntimeException("Category title is required");
        }

        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Category image is required");
        }

        String imageUrl = s3Service.uploadCategoryImage(image);

        Category category = new Category();
        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        category.setImageUrl(imageUrl);


        repository.save(category);
        

        return mapToResponseadd(category);
    }
//    Method for Add
    private AddCategoryResponse mapToResponseadd(Category category) {
    	
        AddCategoryResponse responseadd=new AddCategoryResponse();
        
        responseadd.setTitle(category.getTitle());
        responseadd.setImageUrl(category.getImageUrl());
        responseadd.setDescription(category.getDescription());
        
        return responseadd;
    }
//    Edit
    public EditCategoryResponse update(Long id,AddCategoryRequest request,MultipartFile image)
    {
    	Category category=repository.findById(id).
    			orElseThrow(()->new RuntimeException("Category not Found..."));
    	
    	if (image != null && !image.isEmpty()) {
            String imageUrl = s3Service.uploadCategoryImage(image);
            category.setImageUrl(imageUrl);
        }
    	
    	category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        
        repository.save(category);
        
        return mapToResponseedit(category);

    }
//    Method for edit
    private EditCategoryResponse mapToResponseedit(Category category) {
        EditCategoryResponse responseedit=new EditCategoryResponse();
        
        
        responseedit.setTitle(category.getTitle());
        responseedit.setImageUrl(category.getImageUrl());
        responseedit.setDescription(category.getDescription());
        
        return responseedit;
    }
//    Category/list,search
    public Page<ListCategoryResponse> list(String search, Pageable pageable) {
        Page<Category> page = (search == null || search.isEmpty())
                ? repository.findAllByActiveTrue(pageable)
                : repository.findByTitleContainingIgnoreCase(search, pageable);

        return page.map(this::mapToResponse);
    }
//    Method for Category
    private ListCategoryResponse mapToResponse(Category category) {
        ListCategoryResponse response = new ListCategoryResponse();
        response.setTitle(category.getTitle());
        response.setImageUrl(category.getImageUrl());
        return response;
    }
//    delete
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
//    View Details
   public ViewCategoryResponse getById(Long id)
   {
	   Category category=repository.findById(id).orElseThrow(()->new RuntimeException("Category not found..."));
	   
	   return new ViewCategoryResponse(category.getId(),			   
			   category.getTitle(),
			   category.getDescription(),
			   category.getImageUrl()
			   );
	   
   }
// Drop down fetching for product creation 
 public List<Category> getActiveCategories() {
     return repository.findAll();
 }

   
}



