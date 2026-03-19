package com.seven.fitz.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seven.fitz.entity.Color;
import com.seven.fitz.repository.ColorRepository;


@Service
public class AdminColorService {

    private final ColorRepository colorRepository;
    
    public AdminColorService(ColorRepository repository)
    {
    	this.colorRepository=repository;
    	
    }

    // ADD COLOR
    public Color create(Color color) {

        if (colorRepository.existsByNameIgnoreCase(color.getName())) {
            throw new RuntimeException("Color already exists");
        }

        return colorRepository.save(color);
    }

    // LIST / SEARCH
    public List<Color> list(String search) {
        if (search == null || search.isBlank()) {
            return colorRepository.findAll();
        }
        return colorRepository.findByNameContainingIgnoreCase(search);
    }

    // DELETE
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}