package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.FeeTypeRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.FeeTypeResponse;
import com.hust.smart_apartment.dto.response.VehicleTypeResponse;
import com.hust.smart_apartment.entity.FeeType;
import com.hust.smart_apartment.entity.VehicleType;
import com.hust.smart_apartment.mapper.FeeTypeMapper;
import com.hust.smart_apartment.repository.FeeTypeRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.repository.VehicleTypeRepository;
import com.hust.smart_apartment.service.FeeTypeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeeTypeServiceImpl implements FeeTypeService {
    private final FeeTypeRepository feeTypeRepository;
    private final SearchRepository<FeeTypeResponse> searchRepository;
    private final FeeTypeMapper feeTypeMapper;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public FeeTypeResponse getById(Long id) {
        FeeType feeType = feeTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FeeType with id " + id + " not found"));
        return feeTypeMapper.entityToResponse(feeType);
    }

    @Override
    public FeeTypeResponse create(FeeTypeRequest request) {
        FeeType feeType = feeTypeMapper.requestToEntity(request);
        feeType = feeTypeRepository.save(feeType);
        return feeTypeMapper.entityToResponse(feeType);
    }

    @Override
    public FeeTypeResponse update(Long id, FeeTypeRequest request) {
        FeeType feeType = feeTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FeeType with id " + id + " not found"));
        feeType.setDescription(request.getDescription());
        feeType.setUnitPrice(request.getUnitPrice());
        feeType = feeTypeRepository.save(feeType);
        return feeTypeMapper.entityToResponse(feeType);
    }

    @Override
    @Transactional
    public ModifyDto update(FeeCategory category, FeeTypeRequest request) {
        if (FeeCategory.services.contains(category)) {
            FeeType feeType = feeTypeRepository.findByCategory(category).orElseThrow(() -> new EntityNotFoundException("FeeType with category " + category + " not found"));
            feeType.setDescription(request.getDescription());
            feeType.setUnitPrice(request.getUnitPrice());
            feeTypeRepository.save(feeType);
            return new ModifyDto(1);
        } else if (FeeCategory.parkings.contains(category)) {
            VehicleType vehicleType = vehicleTypeRepository.findByFeeCategory(category).orElseThrow(() -> new EntityNotFoundException("VehicleType with category " + category + " not found"));
            vehicleType.setUnitPrice(request.getUnitPrice());
            vehicleTypeRepository.save(vehicleType);
            return new ModifyDto(1);
        }
        return new ModifyDto(0);
    }

    @Override
    public ModifyDto delete(Long id) {
        Optional<FeeType> feeType = feeTypeRepository.findById(id);
        if (feeType.isEmpty()) {
            throw new EntityNotFoundException("FeeType with id " + id + " not found");
        }
        feeTypeRepository.deleteById(id);
        return new ModifyDto(1);
    }

    @Override
    public Page<FeeTypeResponse> search(SearchRequest request) {
        return searchRepository.search(request, FeeTypeResponse.class);
    }
}
