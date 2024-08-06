package com.t0khyo.clothing_store.repository;

import com.t0khyo.clothing_store.model.entity.CategoricalSlider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoricalSliderRepository extends JpaRepository<CategoricalSlider, Long> {

    List<CategoricalSlider> findAllByCategory(String category);
}
