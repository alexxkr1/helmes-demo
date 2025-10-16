package com.alex.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "sector")
@Entity
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
}
