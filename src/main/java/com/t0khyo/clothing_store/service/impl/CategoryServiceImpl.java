package com.t0khyo.clothing_store.service.impl;

import com.t0khyo.clothing_store.mapper.ObjectMapper;
import com.t0khyo.clothing_store.model.dto.CategoryRequest;
import com.t0khyo.clothing_store.model.entity.Category;
import com.t0khyo.clothing_store.repository.CategoryRepository;
import com.t0khyo.clothing_store.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + id)
        );
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(CategoryRequest categoryRequest) {
        if (categoryRepository.findByTitle(categoryRequest.title()).isPresent()) {
            throw new DataIntegrityViolationException("category title must be unique!, there is already a category with title: " + categoryRequest.title());
        }
        Category category = objectMapper.toEntity(categoryRequest);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        Category category = this.getById(id);
        categoryRepository.delete(category);
    }
}
