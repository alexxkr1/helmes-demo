package com.alex.demo.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public record UpsertSubmissionRequestDTO(
        Long id,

        @NotBlank
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
        String name,

        @NotEmpty(message = "You must select at least one sector.")
        List<Long> sectors,

        @NotNull
        @AssertTrue(message = "You must agree to the terms.")
        Boolean agreedToTerms
) { }