package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.ApartmentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.request.UpdateApartmentRequest;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ApartmentService {

    ApartmentResponse getById(Long id);
    ApartmentResponse create(ApartmentRequest request);
    ApartmentResponse update(Long id, UpdateApartmentRequest request);
    ModifyDto delete(Long id);
    Page<ApartmentResponse> search(SearchRequest request);
}
