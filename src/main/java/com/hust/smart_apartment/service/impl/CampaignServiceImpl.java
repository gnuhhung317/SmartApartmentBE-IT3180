package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.CampaignRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.CampaignResponse;
import com.hust.smart_apartment.entity.Campaign;
import com.hust.smart_apartment.mapper.CampaignMapper;
import com.hust.smart_apartment.repository.CampaignRepository;
import com.hust.smart_apartment.repository.PeriodRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.CampaignService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    private final PeriodRepository periodRepository;

    private final CampaignMapper campaignMapper;

    private final SearchRepository<CampaignResponse> searchRepository;

    @Override
    public CampaignResponse getById(Long id) {
        return campaignMapper.entityToResponse(campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id: " + id)));
    }

    @Override
    public CampaignResponse create(CampaignRequest request) {
        Campaign campaign = campaignMapper.requestToEntity(request);
//        campaign.setPeriod(periodRepository.findById(request.getPeriodId()).orElseThrow(() -> new EntityNotFoundException("id: " + request.getPeriodId())));
        campaign = campaignRepository.save(campaign);

        return campaignMapper.entityToResponse(campaign);
    }

    @Override
    public CampaignResponse update(Long id, CampaignRequest request) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id: " + id));
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        return campaignMapper.entityToResponse(campaignRepository.save(campaign));
    }

    @Override
    public ModifyDto delete(Long id) {
        if (campaignRepository.existsById(id)) {
            campaignRepository.deleteById(id);
            return new ModifyDto(1);
        }
        return new ModifyDto(0);
    }

    @Override
    public Page<CampaignResponse> search(SearchRequest request) {
        Page<CampaignResponse> campaigns = searchRepository.search(request, CampaignResponse.class);
        return campaigns;
    }
}
