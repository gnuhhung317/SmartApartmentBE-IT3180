package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.request.ChangeLivingTypeRequest;
import com.hust.smart_apartment.dto.request.ResidentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.ResidentChangeLogResponse;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ResidentService {
    ResidentChangeLogResponse changeLivingType(Long residentId, ChangeLivingTypeRequest request);

    ResidentResponse getById(Long id);

    ResidentResponse insertToApartment(ResidentRequest request,Long apartmentId);

    ResidentResponse update(Long id, ResidentRequest request);

    Page<ResidentResponse> search(SearchRequest request);
}
