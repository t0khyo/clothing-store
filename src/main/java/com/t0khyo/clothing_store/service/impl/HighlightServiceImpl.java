package com.t0khyo.clothing_store.service.impl;

import com.t0khyo.clothing_store.mapper.ImageMapper;
import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import com.t0khyo.clothing_store.model.entity.Highlight;
import com.t0khyo.clothing_store.model.enums.Category;
import com.t0khyo.clothing_store.model.enums.ContentType;
import com.t0khyo.clothing_store.repository.HighlightRepository;
import com.t0khyo.clothing_store.service.HighlightService;
import com.t0khyo.clothing_store.util.ImageUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HighlightServiceImpl implements HighlightService {
    private final ImageUtil imageUtil;
    private final HighlightRepository highlightRepository;
    private final ImageMapper imageMapper;
    @Value("${highlight.dir}")
    private String highlightDir;

    public HighlightResponse save(MultipartFile image, String title, ContentType contentType, Category category) throws IOException {
        String imagePath = imageUtil.saveImage(image, highlightDir);

        Highlight highlight = Highlight.builder()
                .title(title)
                .imagePath(imagePath)
                .category(category)
                .content(contentType)
                .build();

        Highlight savedHighlight = highlightRepository.save(highlight);

        return imageMapper.toDto(savedHighlight);
    }

    @Override
    public List<HighlightResponse> getAllByCategory(Category category) {
        return highlightRepository.findAllByCategory(category).stream().map(imageMapper::toDto).toList();
    }

    @Override
    public HighlightResponse getById(Long id) {
        Highlight highlight = highlightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Highlight With id: " + id + " not found."));
        return imageMapper.toDto(highlight);
    }

    @Override
    public List<HighlightResponse> getAll() {
        return highlightRepository.findAll().stream().map(imageMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Highlight highlight = highlightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Highlight With id: " + id + " not found."));

        imageUtil.deleteImage(highlight.getImagePath());
        highlightRepository.delete(highlight);
    }

    @Override
    public Resource getImageById(Long id) throws IOException {
        Highlight highlight = highlightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Highlight With id: " + id + " not found."));

        log.info("getImageById(): loading image with path: {}", highlight.getImagePath());

        return imageUtil.loadImage(highlight.getImagePath());
    }
}
