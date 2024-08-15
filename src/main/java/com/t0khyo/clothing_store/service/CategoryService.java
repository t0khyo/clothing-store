package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.CategoryRequest;
import com.t0khyo.clothing_store.model.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getById(Long id);

    List<Category> getAll();

    Category save(CategoryRequest categoryRequest);

    void deleteById(Long id);

}
