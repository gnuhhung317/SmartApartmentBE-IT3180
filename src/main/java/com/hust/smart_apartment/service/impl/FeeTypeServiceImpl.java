package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.FeeTypeRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.FeeTypeResponse;
import com.hust.smart_apartment.entity.FeeType;
import com.hust.smart_apartment.mapper.FeeTypeMapper;
import com.hust.smart_apartment.repository.FeeTypeRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.FeeTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeTypeServiceImpl implements FeeTypeService {
    private final FeeTypeRepository feeTypeRepository;
    private final SearchRepository<FeeTypeResponse> searchRepository;
    private final FeeTypeMapper feeTypeMapper;

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
