package com.t0khyo.clothing_store.model.entity;

import com.t0khyo.clothing_store.model.enums.Category;
import com.t0khyo.clothing_store.model.enums.ContentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Highlight {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imagePath;

    private ContentType content;

    private Category category;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private HighlightGroup highlightGroup;

    @CreationTimestamp
    private LocalDateTime creationDateTime;
}

