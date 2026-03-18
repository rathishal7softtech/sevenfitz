package com.seven.fitz.admin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seven.fitz.admin.DTO.AddSubCategoryRequest;
import com.seven.fitz.admin.DTO.AddSubCategoryResponse;
import com.seven.fitz.admin.DTO.ListSubCategoryResponse;
import com.seven.fitz.admin.DTO.SubcategoryDropdownResponse;
import com.seven.fitz.admin.DTO.ViewSubCategoryResponse;
import com.seven.fitz.admin.service.AdminSubCategoryService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/product/subcategory")
public class AdminSubCategoryController {
	
	@Autowired
	private AdminSubCategoryService service;
	
	@GetMapping("/dropdown")
	public List<SubcategoryDropdownResponse> dropdown(HttpSession session) {
	    return service.getDropdown();
	}
	@PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AddSubCategoryResponse add(

            @RequestPart("title") String title,
            @RequestPart("description") String description,
            @RequestPart("categoryId") Long categoryId,
            @RequestPart(value="image", required=false) MultipartFile image,
            HttpSession session) {

        AddSubCategoryRequest request = new AddSubCategoryRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setCategoryId(categoryId);

        return service.create(request, image);
    }
    
    @GetMapping("/page")
    public Page<ListSubCategoryResponse> getSubCategories(
            @RequestPart(required = false) String search,
            @RequestPart(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestPart(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,HttpSession session
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return service.list(
                search, startDate, endDate, pageable
        );
    }
    
    @GetMapping("/view/{id}")
    public ViewSubCategoryResponse view(@PathVariable Long id,HttpSession session) {
        return service.getById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id,HttpSession session) {
        service.delete(id);
    }
    
    
}
