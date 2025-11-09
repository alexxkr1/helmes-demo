package com.alex.demo.controller;

import com.alex.demo.dto.SectorDTO;
import com.alex.demo.entity.Sector;
import com.alex.demo.service.SectorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
@RequiredArgsConstructor
@Validated
public class SectorController {
    private final SectorService service;

    @Operation(summary = "Get all sectors")
    @GetMapping
    public ResponseEntity<List<SectorDTO>> getAll() {
        List<SectorDTO> sectors = service.findAll();

        return ResponseEntity.ok(sectors); 
    }
}
