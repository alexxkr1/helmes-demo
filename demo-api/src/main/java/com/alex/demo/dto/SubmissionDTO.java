package com.alex.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDTO {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

    @NotEmpty
    private List<Long> sectors;

    @NotNull
    @AssertTrue(message = "You must agree to the terms")
    private Boolean agreedToTerms;
}
