package com.alex.demo.controller;

import com.alex.demo.dto.SubmissionDTO;
import com.alex.demo.entity.Submission;
import com.alex.demo.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionService service;

    public SubmissionController(SubmissionService service) { this.service = service; }

    @Operation(summary = "Create new Submission or Update if id is present")
    @PostMapping
    public ResponseEntity<Submission> createOrUpdate(@Valid @RequestBody SubmissionDTO dto) {
        Submission saved = service.save(dto);

        return ResponseEntity.created(URI.create("/api/submissions" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionDTO> getSubmission(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
