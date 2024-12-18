package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.request.FloorRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.FloorResponse;
import com.hust.smart_apartment.entity.Building;
import com.hust.smart_apartment.entity.Floor;
import com.hust.smart_apartment.mapper.FloorMapper;
import com.hust.smart_apartment.repository.ApartmentRepository;
import com.hust.smart_apartment.repository.BuildingRepository;
import com.hust.smart_apartment.repository.FloorRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.FloorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {


    private final FloorRepository floorRepository; // Assuming there is a JPA repository for Floor
    private final BuildingRepository buildingRepository; // Assuming there is a JPA repository for Building.
    private final FloorMapper floorMapper;
    private final SearchRepository<FloorResponse> searchRepository;


    @Override
    @Transactional
    public FloorResponse create(FloorRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("FloorRequest cannot be null");
        }

        // Map FloorRequest to Floor entity (you may need to create a mapping layer or use something like MapStruct)
        Building building = buildingRepository.findById(Long.parseLong(request.getBuildingId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid building ID"));

        Floor floor = floorMapper.requestToEntity(request);
        floor.setBuilding(building);

        return floorMapper.entityToResponse(floorRepository.save(floor));
    }

    @Override
    public Page<FloorResponse> search(SearchRequest request) {
        return searchRepository.search(request,FloorResponse.class);
    }

}
