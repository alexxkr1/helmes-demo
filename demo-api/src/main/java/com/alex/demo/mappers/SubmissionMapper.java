package com.alex.demo.mappers;

import com.alex.demo.dto.SubmissionDTO;
import com.alex.demo.dto.SubmissionResponseDTO;
import com.alex.demo.dto.UpsertSubmissionRequestDTO;
import com.alex.demo.entity.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SectorMapper.class})
public interface SubmissionMapper {

    @Mapping(target = "sectors", source = "sectors")
    SubmissionResponseDTO toResponseDto(Submission dto);

    @Mapping(target = "id", ignore = true) // Don't map the ID
    @Mapping(target = "sectors", ignore = true) // Handled manually
    void updateFromDto(@MappingTarget Submission submission, UpsertSubmissionRequestDTO dto);

    @Mapping(target = "sectors", ignore = true)
    Submission toEntity(UpsertSubmissionRequestDTO dto);
}
