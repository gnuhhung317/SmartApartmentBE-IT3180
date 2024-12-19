package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.LivingType;
import com.hust.smart_apartment.dto.request.ChangeLivingTypeRequest;
import com.hust.smart_apartment.dto.request.ResidentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.ResidentChangeLogResponse;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.entity.Resident;
import com.hust.smart_apartment.entity.ResidentChangeLog;
import com.hust.smart_apartment.mapper.ApartmentMapper;
import com.hust.smart_apartment.mapper.ResidentChangeLogMapper;
import com.hust.smart_apartment.mapper.ResidentMapper;
import com.hust.smart_apartment.repository.ApartmentRepository;
import com.hust.smart_apartment.repository.ResidentChangeLogRepository;
import com.hust.smart_apartment.repository.ResidentRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.ResidentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
    private final ApartmentRepository apartmentRepository;
    private final ResidentRepository residentRepository;
    private final ResidentChangeLogRepository residentChangeLogRepository;
    private final ResidentChangeLogMapper changeLogMapper;
    private final ResidentMapper residentMapper;
    private final ApartmentMapper apartmentMapper;
    private final SearchRepository<ResidentResponse> searchRepository;


    @Override
    public ResidentResponse getById(Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident with ID " + id + " not found"));
        return residentMapper.entityToResponse(resident);
    }

    @Override
    public ResidentResponse update(Long id, ResidentRequest request) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident with ID " + id + " not found"));

        resident.setFullName(request.getFullName());
        resident.setDateOfBirth(request.getDateOfBirth());
        resident.setIdentityCardNumber(request.getIdentityCardNumber());
        resident.setCurrentLivingType(request.getCurrentLivingType());
        resident.setHouseholdRole(request.getHouseholdRole());

        resident = residentRepository.save(resident);
        return residentMapper.entityToResponse(resident);
    }


    @Override
    public ResidentChangeLogResponse changeLivingType(Long residentId, ChangeLivingTypeRequest request) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new RuntimeException("Resident with ID " + residentId + " not found"));

        ResidentChangeLog changeLog = new ResidentChangeLog();
        changeLog.setResident(residentMapper.entityToResponse(resident));
        changeLog.setApartment(apartmentMapper.entityToResponse(resident.getLivingApartment()));
        changeLog.setLastType(resident.getCurrentLivingType());
        changeLog.setChangeType(request.getNewLivingType());
        changeLog.setChangeDate(LocalDateTime.now());
        changeLog.setNotes(request.getNotes());

        return changeLogMapper.entityToResponse(residentChangeLogRepository.save(changeLog));
    }


    @Override
    @Transactional
    public ResidentResponse insertToApartment(ResidentRequest request, Long apartmentId) {

        
        Resident resident = residentMapper.requestToEntity(request);
        resident.setLivingApartment(apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new RuntimeException("Apartment with ID " + apartmentId + " not found")));
        resident = residentRepository.save(resident);


        ResidentChangeLog changeLog = new ResidentChangeLog();
        changeLog.setResident(residentMapper.entityToResponse(resident));
        changeLog.setApartment(apartmentMapper.entityToResponse(resident.getLivingApartment()));
        changeLog.setLastType(LivingType.O_NGOAI);
        changeLog.setChangeType(resident.getCurrentLivingType());
        changeLog.setChangeDate(LocalDateTime.now());
        changeLog.setNotes("Insert to apartment");
        residentChangeLogRepository.save(changeLog);

        return residentMapper.entityToResponse(resident);
    }


    @Override
    public Page<ResidentResponse> search(SearchRequest request) {
        return searchRepository.search(request, ResidentResponse.class);
    }
}
