package com.alex.demo.service;

import com.alex.demo.dto.SectorDTO;
import com.alex.demo.entity.Sector;
import com.alex.demo.mappers.SectorMapper;
import com.alex.demo.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository repo;
    private final SectorMapper sectorMapper;

    @Transactional(readOnly = true)
    public List<SectorDTO> findAll() {
        List<Sector> allSectors = repo.findAll();
        Map<Long, List<Sector>> childrenMap = new HashMap<>();
        List<Sector> roots = new ArrayList<>();

        for (Sector s : allSectors) {
            if (s.getParentId() == null) {
                roots.add(s);
            } else {
                childrenMap.computeIfAbsent(s.getParentId(), k -> new ArrayList<>()).add(s);
            }
        }

        List<Sector> result = new ArrayList<>();
        for (Sector root : roots) {
            addWithChildren(root, childrenMap, result);
        }

        return sectorMapper.toDtoList(result);
    }

    private void addWithChildren(Sector parent, Map<Long, List<Sector>> childrenMap, List<Sector> result) {
        result.add(parent);
        List<Sector> children = childrenMap.get(parent.getId());
        if (children != null) {
            children.sort(Comparator.comparing(Sector::getId));

            for (Sector child : children) {
                addWithChildren(child, childrenMap, result);
            }
        }
    }

}

