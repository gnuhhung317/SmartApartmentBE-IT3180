package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.DonationRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.DonationResponse;
import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.entity.Campaign;
import com.hust.smart_apartment.entity.Donation;
import com.hust.smart_apartment.mapper.DonationMapper;
import com.hust.smart_apartment.repository.ApartmentRepository;
import com.hust.smart_apartment.repository.CampaignRepository;
import com.hust.smart_apartment.repository.DonationRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.DonationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;
    private final ApartmentRepository apartmentRepository;
    private final CampaignRepository campaignRepository;
    private final DonationMapper donationMapper;
    private final SearchRepository<DonationResponse> searchRepository;
    @Override
    @Transactional
    public DonationResponse create(DonationRequest request) {
        Donation donation = donationMapper.requestToEntity(request);
        Apartment apartment = apartmentRepository.findById(request.getApartmentId()).orElseThrow(() ->  new EntityNotFoundException("Apartment not found with id: " + request.getApartmentId()));
        Campaign campaign = campaignRepository.findById(request.getCampaignId()).orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + request.getApartmentId()));
        campaign.addMoney(donation.getAmount().longValue());
        campaign = campaignRepository.save(campaign);
        donation.setApartment(apartment);
        donation.setCampaign(campaign);
        donationRepository.save(donation);

        return donationMapper.entityToResponse(donation);
    }

    @Override
    @Transactional
    public ModifyDto delete(Long id) {
         Donation donation = donationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Donation not found with id: "+id));
         Campaign campaign = donation.getCampaign();
         campaign.subtractMoney(donation.getAmount().longValue());
         campaignRepository.save(campaign);
         donationRepository.delete(donation);
        return new ModifyDto(1);
    }

    @Override
    public DonationResponse update(Long id, DonationRequest request) {
        return null;
    }

    @Override
    public DonationResponse getById(Long id) {
        return donationMapper.entityToResponse(donationRepository.findById(id).orElse(null));
    }

    @Override
    public Page<DonationResponse> search(SearchRequest request) {
        return searchRepository.search(request,DonationResponse.class);
    }
}