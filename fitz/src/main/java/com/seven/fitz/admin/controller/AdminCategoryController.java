package com.seven.fitz.admin.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.AddCategoryRequest;
import com.seven.fitz.admin.DTO.AddCategoryResponse;
import com.seven.fitz.admin.DTO.EditCategoryResponse;
import com.seven.fitz.admin.DTO.ListCategoryResponse;
import com.seven.fitz.admin.DTO.ViewCategoryResponse;
import com.seven.fitz.admin.service.AdminCategoryService;
import com.seven.fitz.entity.Category;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/product/category")
public class AdminCategoryController {
	
	private final AdminCategoryService categoryservice;
	
	public AdminCategoryController(AdminCategoryService categoryservice)
	{
		this.categoryservice=categoryservice;
	}
	
	@PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AddCategoryResponse create(
	        @RequestPart("title") String title,
	        @RequestPart("description") String description,
	        @RequestPart("image") MultipartFile image,HttpSession session) {

	    AddCategoryRequest request = new AddCategoryRequest();
	    request.setTitle(title);
	    request.setDescription(description);

	    return categoryservice.create(request, image);
	}
	
	@PutMapping(value= "/edit/{id}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public EditCategoryResponse update(@PathVariable Long id, @ModelAttribute AddCategoryRequest request,
	        @RequestPart(value = "image", required = false) MultipartFile image,HttpSession session)
	{
		return categoryservice.update(id,request, image);
	}
	@GetMapping(value="/page")
    public Page<ListCategoryResponse> list(
            @RequestPart(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,HttpSession session
    ) {
        return categoryservice.list(search, PageRequest.of(page, size));
    }
	
	@DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id,HttpSession session) {
	
        categoryservice.delete(id);
    }
	
	@GetMapping("/view/{id}")
	public ViewCategoryResponse viewdetails(@PathVariable Long id,HttpSession session)
	{
		return categoryservice.getById(id);
	}
	
	 @GetMapping("/dropdown")
	 public List<Category> getCategoriesForDropdown(HttpSession session) {
	    return categoryservice.getActiveCategories();
	 }
	

}
