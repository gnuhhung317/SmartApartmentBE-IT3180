package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.DonationRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.DonationResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DonationService {

    DonationResponse create(DonationRequest request);

    ModifyDto delete(Long id);

    DonationResponse update(Long id, DonationRequest request);

    DonationResponse getById(Long id);

    Page<DonationResponse> search(SearchRequest request);

}
