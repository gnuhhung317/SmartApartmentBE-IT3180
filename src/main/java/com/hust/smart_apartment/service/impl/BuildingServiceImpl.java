package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.BuildingRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.BuildingResponse;
import com.hust.smart_apartment.entity.Building;
import com.hust.smart_apartment.mapper.BuildingMapper;
import com.hust.smart_apartment.repository.BuildingRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;
    private final SearchRepository<BuildingResponse> searchRepository;

    @Override
    public BuildingResponse getById(Long id) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with ID: " + id));
        return buildingMapper.entityToResponse(building);
    }

    @Override
    public BuildingResponse create(BuildingRequest request) {
        Building building = buildingMapper.requestToEntity(request);
        Building savedBuilding = buildingRepository.save(building);
        return buildingMapper.entityToResponse(savedBuilding);
    }

    @Override
    public BuildingResponse update(Long id, BuildingRequest request) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with ID: " + id));

        building.setBuildingCode(request.getBuildingCode());
        building.setBuildingName(request.getBuildingName());

        Building updatedBuilding = buildingRepository.save(building);
        return buildingMapper.entityToResponse(updatedBuilding);
    }

    @Override
    public ModifyDto delete(Long id) {
        var building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with ID: " + id));

        buildingRepository.delete(building);
        return new ModifyDto(1);
    }

    @Override
    public Page<BuildingResponse> search(SearchRequest request) {
        return searchRepository.search(request, BuildingResponse.class);
    }
}
