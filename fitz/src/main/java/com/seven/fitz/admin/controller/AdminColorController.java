package com.seven.fitz.admin.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.admin.service.AdminColorService;
import com.seven.fitz.entity.Color;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/product/colors")
@RequiredArgsConstructor
public class AdminColorController {

    private final AdminColorService colorService;
    
    public AdminColorController(AdminColorService service)
    {
    	this.colorService=service;
    }

 // ADD COLOR
    @PostMapping("/add")
    public Color add(@RequestBody Color color,
                     HttpSession session) {
        return colorService.create(color);
    }

    // LIST COLORS
    @GetMapping("/list")
    public List<Color> list(@RequestParam(required = false) String search,
                            HttpSession session) {
        return colorService.list(search);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id,
                       HttpSession session) {
        colorService.delete(id);
    }
}