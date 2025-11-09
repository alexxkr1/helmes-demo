package com.alex.demo.mappers;

import com.alex.demo.dto.SectorDTO;
import com.alex.demo.entity.Sector;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectorMapper {

    SectorDTO toDto(Sector entity);

    List<SectorDTO> toDtoList(List<Sector> entities);

    default Long map(Sector sector) {
        if (sector == null) {
            return null;
        }
        return sector.getId();
    }
}
