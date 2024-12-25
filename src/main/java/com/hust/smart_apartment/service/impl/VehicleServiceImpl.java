package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.request.VehicleRequest;
import com.hust.smart_apartment.dto.response.VehicleResponse;
import com.hust.smart_apartment.dto.response.VehicleTypeResponse;
import com.hust.smart_apartment.entity.Vehicle;
import com.hust.smart_apartment.entity.VehicleType;
import com.hust.smart_apartment.mapper.VehicleMapper;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.repository.VehicleRepository;
import com.hust.smart_apartment.repository.VehicleTypeRepository;
import com.hust.smart_apartment.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final SearchRepository<VehicleResponse> searchRepository;

    @Override
    public VehicleResponse getById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::entityToResponse)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    @Override
    public VehicleResponse create(VehicleRequest request) {
        Vehicle vehicle = vehicleMapper.requestToEntity(request);
        Optional.ofNullable(request.getVehicleTypeId())
                .ifPresent(vehicleTypeId -> vehicle.setVehicleType(
                        vehicleTypeRepository.findById(vehicleTypeId)
                                .orElseThrow(() -> new EntityNotFoundException("VehicleType not found with id: " + vehicleTypeId))
                ));
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.entityToResponse(savedVehicle);
    }

    @Override
    public VehicleResponse update(Long id, VehicleRequest request) {
        var existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        existingVehicle.setLicensePlate(request.getLicensePlate());
        existingVehicle.setName(request.getName());
        Optional.ofNullable(request.getVehicleTypeId())
                .ifPresent(vehicleTypeId -> existingVehicle.setVehicleType(
                        vehicleTypeRepository.findById(vehicleTypeId)
                                .orElseThrow(() -> new EntityNotFoundException("VehicleType not found with id: " + vehicleTypeId))
                ));
        var updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.entityToResponse(updatedVehicle);
    }

    @Override
    public ModifyDto delete(Long id) {
        try {
            vehicleRepository.deleteById(id);
            return new ModifyDto(1);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Vehicle not found with id: " + id);
        }
    }

    @Override
    public Page<VehicleResponse> search(SearchRequest request) {
        return searchRepository.search(request, VehicleResponse.class);
    }

    @Override
    public VehicleTypeResponse updateVehicleType(Long id, Integer unitPrice) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("VehicleType not found with id: " + id));
        vehicleType.setUnitPrice(unitPrice);
        vehicleType =vehicleTypeRepository.save(vehicleType);
        return VehicleTypeResponse.builder().feeCategory(vehicleType.getFeeCategory()).unitPrice(vehicleType.getUnitPrice()).vehicleTypeId(vehicleType.getVehicleTypeId()).build();
    }

    @Override
    public List<VehicleTypeResponse> getAll() {
        return vehicleTypeRepository.findAll().stream().map(vehicleMapper::vehicleTypeEntityToResponse).toList();
    }

    @Override
    public List<VehicleTypeResponse> createVehicleTypes() {
        List<VehicleType> vehicleTypes = List.of(
                VehicleType.builder().feeCategory(FeeCategory.PARKING_CAR).unitPrice(1000000).build(),
                VehicleType.builder().feeCategory(FeeCategory.PARKING_MOTORCYCLE).unitPrice(100000).build()
        );
        vehicleTypeRepository.saveAll(vehicleTypes);
        return getAll();
    }
}
