package com.alex.demo.dto;

import java.util.List;

public record SubmissionResponseDTO(
        Long id,
        String name,
        List<Long> sectors,
        Boolean agreedToTerms
) { }
