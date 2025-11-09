package com.alex.demo.controller;

import com.alex.demo.dto.SubmissionDTO;
import com.alex.demo.dto.SubmissionResponseDTO;
import com.alex.demo.dto.UpsertSubmissionRequestDTO;
import com.alex.demo.entity.Submission;
import com.alex.demo.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/submissions")
@Validated
public class SubmissionController {
    private final SubmissionService service;

    public SubmissionController(SubmissionService service) { this.service = service; }

    @Operation(summary = "Create new Submission or Update if id is present")
    @PostMapping
    public ResponseEntity<SubmissionResponseDTO> createOrUpdate(@Valid @RequestBody UpsertSubmissionRequestDTO dto) {
        SubmissionResponseDTO saved = service.upsert(dto);

        if(dto.id() != null) {
            return ResponseEntity.ok(saved);
        } else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.id())
                    .toUri();

            return ResponseEntity.created(location).body(saved);
        }
    }

    @Operation(summary = "Get submission by id")
    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponseDTO> getSubmission(@PathVariable Long id) {
        SubmissionResponseDTO dto = service.findById(id);

        return ResponseEntity.ok(dto);
    }

}
