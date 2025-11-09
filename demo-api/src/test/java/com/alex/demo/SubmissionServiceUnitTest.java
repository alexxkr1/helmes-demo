package com.alex.demo;

import com.alex.demo.dto.SubmissionResponseDTO;
import com.alex.demo.dto.UpsertSubmissionRequestDTO;
import com.alex.demo.entity.Sector;
import com.alex.demo.entity.Submission;
import com.alex.demo.exception.ResourceNotFoundException;
import com.alex.demo.repository.SectorRepository;
import com.alex.demo.repository.SubmissionRepository;
import com.alex.demo.service.SubmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubmissionServiceUnitTest {

    @Mock
    private SubmissionRepository submissionRepository;

    @Mock
    private SectorRepository sectorRepository;

    @InjectMocks
    private SubmissionService submissionService;

    private UpsertSubmissionRequestDTO submissionDTO;
    private Sector mockSector;
    private Sector mockSector2;
    private List<Long> sectorIds;

    @BeforeEach
    void setUp() {
        mockSector = new Sector(1L, "Sector1");
        mockSector2 = new Sector(2L, "Sector2");

        sectorIds = Arrays.asList(1L, 2L);

        submissionDTO = new UpsertSubmissionRequestDTO(
                null,
                "Test Name",
                sectorIds,
                true
        );
    }

    @Test
    void saveOrUpdate_shouldMakeNewSubmission_ifIdIsNullAndDataIsValdi(){
        List<Sector> expectedSectorList = new ArrayList<>();
        expectedSectorList.add(mockSector);
        expectedSectorList.add(mockSector2);

        Submission expectedSavedSubmission = new Submission(
                5L,
                submissionDTO.name(),
                expectedSectorList,
                submissionDTO.agreedToTerms()
                );

        when(sectorRepository.findAllById(sectorIds))
                .thenReturn(expectedSectorList);


        when(submissionRepository.save(any(Submission.class)))
                .thenReturn(expectedSavedSubmission);

        SubmissionResponseDTO result = submissionService.upsert(submissionDTO);

        assertNotNull(result.id(), "Returned submission must have ID");
        assertEquals(5L, result.id());
        assertEquals("Test Name", result.name());
        assertTrue(result.agreedToTerms());

        verify(submissionRepository, never()).findById(anyLong());
        verify(submissionRepository, times(1)).save(any(Submission.class));
    }

    @Test
    void saveOrUpdate_shouldThrowNotFoundException_whenUpdatingNonExistentId() {
        Long nonExistentId = 321321L;
        UpsertSubmissionRequestDTO submissionDTO = new UpsertSubmissionRequestDTO(nonExistentId, "Name", sectorIds, true);

        when(submissionRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            submissionService.upsert(submissionDTO);
        });

        verify(submissionRepository, never()).save(any(Submission.class));
    }
}
