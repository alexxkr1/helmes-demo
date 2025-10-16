package com.alex.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "sector")
@Entity
@NoArgsConstructor
public class Sector {
    @Id
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private int level;

    @Column(name = "parent_id")
    @JsonProperty("parent_id")
    private Long parentId;

    public Sector(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
