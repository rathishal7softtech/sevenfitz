package com.seven.fitz.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.seven.fitz.admin.DTO.AddSizeRequest;
import com.seven.fitz.admin.DTO.ListSizeResponse;
import com.seven.fitz.entity.Size;
import com.seven.fitz.repository.SizeRepository;

@Service
public class AdminSizeService {
	
	private final SizeRepository repository;
	
	public AdminSizeService(SizeRepository repository)
	{
		this.repository=repository;
	}
	
//	Add
	public void add(AddSizeRequest request) {

        if (request.getName() == null || request.getName().isBlank()) {
            throw new RuntimeException("Size name is required");
        }

        String sizeName = request.getName().trim();

        if (repository.existsByNameIgnoreCase(sizeName)) {
            throw new RuntimeException("Size already exists");
        }

        Size size = new Size();
        size.setName(sizeName);

        repository.save(size);
    }
//	Page
	public Page<ListSizeResponse> list(int page, int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    Page<Size> result = repository.findAll(pageable);

	    return result.map(s -> new ListSizeResponse(
	            s.getId(),
	            s.getName()
	    ));
	}
	public List<ListSizeResponse> search(String search) {

	    List<Size> sizes = repository.findByNameContainingIgnoreCase(search);

	    return sizes.stream()
	            .map(s -> new ListSizeResponse(
	                    s.getId(),
	                    s.getName()
	            ))
	            .toList();
	}
//	Delete
	 public void delete(Long id) {
	        repository.deleteById(id);
	    }
}
