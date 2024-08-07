package com.t0khyo.clothing_store.repository;

import com.t0khyo.clothing_store.model.entity.Highlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighlightRepository extends JpaRepository<Highlight, Long> {
    List<Highlight> findAllByCategory(String category);
}
