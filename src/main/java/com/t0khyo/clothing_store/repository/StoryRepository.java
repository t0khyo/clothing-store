package com.t0khyo.clothing_store.repository;

import com.t0khyo.clothing_store.model.entity.Story;
import com.t0khyo.clothing_store.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findAllByCreationDateTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);

    List<Story> findAllByCategory(Category category);
}
