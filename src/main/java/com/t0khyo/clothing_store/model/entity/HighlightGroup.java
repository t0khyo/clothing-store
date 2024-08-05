package com.t0khyo.clothing_store.model.entity;

import com.t0khyo.clothing_store.model.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HighlightGroup {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Category category;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Highlight> highlights;

    @CreationTimestamp
    private LocalDateTime creationDateTime;
}
