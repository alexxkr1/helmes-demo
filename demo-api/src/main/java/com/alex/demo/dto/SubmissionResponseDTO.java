package com.alex.demo.dto;

import java.util.List;

public record SubmissionResponseDTO(
        Long id,
        String name,
        Boolean agreedToTerms,
        List<SectorDTO> sectors
) { }
