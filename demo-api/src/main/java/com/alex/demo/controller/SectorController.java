package com.alex.demo.controller;

import com.alex.demo.entity.Sector;
import com.alex.demo.service.SectorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {
    private final SectorService service;

    public SectorController(SectorService service) {
        this.service = service;
    }

    @Operation(summary = "Get all sectors")
    @GetMapping
    public List<Sector> getAll() { return service.findAll(); }
}
