package com.t0khyo.clothing_store.repository;

import com.t0khyo.clothing_store.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
