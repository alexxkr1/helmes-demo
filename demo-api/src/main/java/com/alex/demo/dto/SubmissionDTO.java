package com.alex.demo.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String name;

    @NotEmpty
    private List<Long> sectors;

    @NotNull
    @AssertTrue(message = "You must agree to the terms")
    private Boolean agreedToTerms;
}
