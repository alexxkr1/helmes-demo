package com.alex.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "sector")
@Entity
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private int level;

    private String parent_id;
}
