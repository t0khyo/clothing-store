package com.t0khyo.clothing_store.service.impl;

import com.t0khyo.clothing_store.mapper.ImageMapper;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.model.entity.Story;
import com.t0khyo.clothing_store.repository.StoryRepository;
import com.t0khyo.clothing_store.service.StoryService;
import com.t0khyo.clothing_store.util.ImageUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoryServiceImpl implements StoryService {
    private final ImageUtil imageUtil;
    private final StoryRepository storyRepository;
    private final ImageMapper imageMapper;

    @Override
    public  StoryResponse save(MultipartFile image, String title) throws IOException {
        String imagePath = imageUtil.saveStoryImage(image);

        Story story = Story.builder()
                .title(title)
                .imagePath(imagePath)
                .build();

        Story savedStory = storyRepository.save(story);

        return imageMapper.toDto(savedStory);
    }

    @Override
    public StoryResponse getById(Long id) {
        Story slider = storyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Story With id: " + id + " not found."));
        return imageMapper.toDto(slider);
    }

    @Override
    public List<StoryResponse> getAll() {
        return storyRepository.findAll().stream().map(imageMapper::toDto).toList();
    }

    @Override
    public List<StoryResponse> getAllTodayStories() {
        // returns the stories created till now in the last 24 Hours.
        LocalDateTime toTime = LocalDateTime.now();
        LocalDateTime fromTime = toTime.minusDays(1);

        return storyRepository.findAllByCreationDateTimeBetween(fromTime, toTime).stream().map(imageMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Story story = storyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Story With id: " + id + " not found."));

        imageUtil.deleteImage(story.getImagePath());
        storyRepository.delete(story);
    }

    @Override
    public Resource getImageById(Long id) throws IOException {
        Story story = storyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Story With id: " + id + " not found."));

        log.info("getImageById(): loading image with path: {}", story.getImagePath());

        return imageUtil.loadImage(story.getImagePath());
    }
}
