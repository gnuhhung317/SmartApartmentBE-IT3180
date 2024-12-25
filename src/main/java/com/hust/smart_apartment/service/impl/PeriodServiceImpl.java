package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.PeriodRequest;
import com.hust.smart_apartment.dto.request.PeriodRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.PeriodResponse;
import com.hust.smart_apartment.dto.response.PeriodResponse;
import com.hust.smart_apartment.entity.Period;
import com.hust.smart_apartment.mapper.PeriodMapper;
import com.hust.smart_apartment.repository.PeriodRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.PeriodService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService {
    private final PeriodRepository periodRepository;

    private final PeriodMapper periodMapper;

    private final SearchRepository<PeriodResponse> searchRepository;

    @Override
    public PeriodResponse getById(Long id) {
        return periodMapper.entityToResponse(periodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id: " + id)));
    }

    @Override
    public PeriodResponse create(PeriodRequest request) {
        Period period = periodMapper.requestToEntity(request);
        period = periodRepository.save(period);

        return periodMapper.entityToResponse(period);
    }

    @Override
    public PeriodResponse update(Long id, PeriodRequest request) {
        Period period = periodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id: " + id));
        period.setName(request.getName());
        return periodMapper.entityToResponse(periodRepository.save(period));
    }

    @Override
    public ModifyDto delete(Long id) {
        if (periodRepository.existsById(id)) {
            periodRepository.deleteById(id);
            return new ModifyDto(1);
        }
        return new ModifyDto(0);
    }

    @Override
    public Page<PeriodResponse> search(SearchRequest request) {
        Page<PeriodResponse> periods = searchRepository.search(request, PeriodResponse.class);
        return periods;
    }
}
