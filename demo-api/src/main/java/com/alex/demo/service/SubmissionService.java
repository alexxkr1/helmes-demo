package com.alex.demo.service;

import com.alex.demo.dto.SubmissionResponseDTO;
import com.alex.demo.dto.UpsertSubmissionRequestDTO;
import com.alex.demo.entity.Sector;
import com.alex.demo.entity.Submission;
import com.alex.demo.exception.ResourceNotFoundException;
import com.alex.demo.mappers.SubmissionMapper;
import com.alex.demo.repository.SectorRepository;
import com.alex.demo.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository repo;
    private final SectorRepository sectorRepo;
    private final SubmissionMapper submissionMapper;

    @Transactional
    public SubmissionResponseDTO upsert(UpsertSubmissionRequestDTO dto) {
        Submission s;

        if (dto.id() != null){
            s = repo.findById(dto.id()).orElseThrow(() -> new ResourceNotFoundException("Submission", dto.id()));

            submissionMapper.updateFromDto(s, dto);
        } else {
            s = submissionMapper.toEntity(dto);
        }

        List<Sector> selectedSectors = sectorRepo.findAllById(dto.sectors());

        if (selectedSectors.size() != dto.sectors().size()) {
            throw new ResourceNotFoundException("One or more Sector IDs are not valid");
        }

        s.setSectors(selectedSectors);
        Submission saved = repo.save(s);
        return submissionMapper.toResponseDto(saved);

    }

    @Transactional(readOnly = true)
    public SubmissionResponseDTO findById(Long id) {
        Submission submission = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission with ID " + id + " not found"));

        return submissionMapper.toResponseDto(submission);
    }
}
