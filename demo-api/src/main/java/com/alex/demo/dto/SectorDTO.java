package com.alex.demo.dto;

public record SectorDTO(
        Long id,
        String label,
        int level,
        Long parentId
) {}
