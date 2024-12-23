package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.CampaignRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.CampaignResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CampaignService {
    CampaignResponse getById(Long id);
    CampaignResponse create(CampaignRequest request);
    CampaignResponse update(Long id,CampaignRequest request);
    ModifyDto delete(Long id);
    Page<CampaignResponse> search(SearchRequest request);
}
