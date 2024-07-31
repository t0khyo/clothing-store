package com.t0khyo.clothing_store.repository;

import com.t0khyo.clothing_store.model.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
