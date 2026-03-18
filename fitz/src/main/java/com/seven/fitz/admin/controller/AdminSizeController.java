package com.seven.fitz.admin.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.admin.DTO.AddSizeRequest;
import com.seven.fitz.admin.DTO.ListSizeRequest;
import com.seven.fitz.admin.DTO.ListSizeResponse;
import com.seven.fitz.admin.service.AdminSizeService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/admin/product/size")
public class AdminSizeController {
	
	private final AdminSizeService service;
	
	
	public AdminSizeController(AdminSizeService service) {
        this.service = service;
    }

    //ADD 
    @PostMapping("/add")
    public String add(@RequestBody AddSizeRequest request,HttpSession session) {
        service.add(request);
        return "Size added successfully";
    }
    
    
    @PostMapping("/list")
    public Page<ListSizeResponse> list(@RequestBody ListSizeRequest request) {

        return service.list(request.getPage(), request.getSize());
    }
    
    @PostMapping("/sizes/search")
    public List<ListSizeResponse> search(@RequestBody ListSizeRequest request) {

        return service.search(request.getSearch());
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id,HttpSession session) {
        service.delete(id);
    }

    
}
