package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.request.ChangeLivingTypeRequest;
import com.hust.smart_apartment.dto.response.ResidentChangeLogResponse;
import org.springframework.stereotype.Service;

@Service
public interface ResidentService {
    ResidentChangeLogResponse changeLivingType(Long residentId, ChangeLivingTypeRequest request);
}
