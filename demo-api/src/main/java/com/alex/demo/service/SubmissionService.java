package com.alex.demo.service;

import com.alex.demo.dto.SubmissionDTO;
import com.alex.demo.entity.Sector;
import com.alex.demo.entity.Submission;
import com.alex.demo.repository.SectorRepository;
import com.alex.demo.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
    private final SubmissionRepository repo;
    private final SectorRepository sectorRepo;

    public SubmissionService(SubmissionRepository repo, SectorRepository sectorRepo) {
        this.repo = repo;
        this.sectorRepo = sectorRepo;
    }

    public Submission save(SubmissionDTO dto) {
        Submission s = new Submission();

        if (dto.getId() != null){
            s = repo.findById(dto.getId()).orElse(new Submission());
        }

        s.setName(dto.getName());
        s.setAgreedToTerms(dto.getAgreedToTerms());

        List<Sector> selectedSectors = sectorRepo.findAllById(dto.getSectors());
        s.setSectors(selectedSectors);

        return repo.save(s);
    }

    public Optional<SubmissionDTO> findById(Long id) {
        return repo.findById(id).map(s -> new SubmissionDTO(
                s.getId(),
                s.getName(),
                s.getSectors().stream().map(Sector::getId).toList(),
                s.getAgreedToTerms()
        ));
    }
}
