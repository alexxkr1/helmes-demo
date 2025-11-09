package com.alex.demo.config;

import com.alex.demo.entity.Sector;
import com.alex.demo.repository.SectorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final SectorRepository sectorRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        if (sectorRepository.count() > 0) {
            System.out.println("Sectors already in database, skipping seed.");
            return;
        }

        System.out.println("Seeding database with sectors...");

        try (InputStream inputStream = new ClassPathResource("data/sectors.json").getInputStream()) {
            List<Sector> sectors = objectMapper.readValue(inputStream, new TypeReference<List<Sector>>() {});
            sectorRepository.saveAllAndFlush(sectors);
            System.out.println("Seeded " + sectors.size() + " sectors successfully!");
        } catch (Exception e) {
            System.err.println("Failed to seed database: " + e.getMessage());
        }
    }
}
