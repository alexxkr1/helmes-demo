package com.alex.demo.service;

import com.alex.demo.entity.Sector;
import com.alex.demo.repository.SectorRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service
public class SectorService {
    private final SectorRepository repo;

    public SectorService(SectorRepository repo) {
        this.repo = repo;
    }

    public List<Sector> findAll() {
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

        return result;
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
