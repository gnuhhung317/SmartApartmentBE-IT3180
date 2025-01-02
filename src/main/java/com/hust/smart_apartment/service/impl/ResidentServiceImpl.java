package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.HouseholdRole;
import com.hust.smart_apartment.constants.LivingType;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.ChangeLivingTypeRequest;
import com.hust.smart_apartment.dto.request.ResidentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import com.hust.smart_apartment.dto.response.ResidentChangeLogResponse;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.entity.Apartment;
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
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final SearchRepository<ResidentChangeLogResponse> searchChangeLogRepository;


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
        resident.setGender(request.getGender());
        resident.setContact(request.getContact());
        resident.setHometown(request.getHometown());
        resident.setJob(request.getJob());

        resident = residentRepository.save(resident);
        return residentMapper.entityToResponse(resident);
    }


    @Override
    public ResidentChangeLogResponse changeLivingType(Long residentId, ChangeLivingTypeRequest request) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new RuntimeException("Resident with ID " + residentId + " not found"));

        Date changeDate =   residentChangeLogRepository.findResidentChangeLogByChangeDateMax();
//        if(resident.getCurrentLivingType().equals(request.getNewLivingType())) {
//            return null;
//        }
        if(changeDate.before(request.getChangeDate())) {
            resident.setCurrentLivingType(request.getNewLivingType());
            resident = residentRepository.save(resident);
        }

        // create change log
        ResidentChangeLog changeLog = new ResidentChangeLog();
        changeLog.setResident(residentMapper.entityToResponse(resident));
        changeLog.setApartment(apartmentMapper.entityToResponse(resident.getLivingApartment()));
        changeLog.setResidentId(residentId);
        changeLog.setApartmentId(changeLog.getApartment().getApartmentId());
        changeLog.setLastType(resident.getCurrentLivingType());
        changeLog.setChangeType(request.getNewLivingType());
        changeLog.setChangeDate(request.getChangeDate());
        changeLog.setNotes(request.getNotes());

        return changeLogMapper.entityToResponse(residentChangeLogRepository.save(changeLog));
    }

    @Override
    public ResidentChangeLogResponse updateLivingType(Long changeLogId, ChangeLivingTypeRequest request) {
        ResidentChangeLog changeLog = residentChangeLogRepository.findById(changeLogId)
                .orElseThrow(() -> new RuntimeException("Change log with ID " + changeLogId + " not found"));

        changeLog.setChangeType(request.getNewLivingType());
        changeLog.setChangeDate(request.getChangeDate());
        changeLog.setNotes(request.getNotes());

        return changeLogMapper.entityToResponse(residentChangeLogRepository.save(changeLog));
    }

    @Override
    public ModifyDto deleteLivingType(Long changeLogId) {
        residentChangeLogRepository.deleteById(changeLogId);
        return new ModifyDto(1);
    }

    @Override
    @Transactional
    public List<ResidentResponse> insertToApartment(List<ResidentRequest> request, Long apartmentId) {

        List<Resident> residents = request.stream().map(residentMapper::requestToEntity).toList();
        Apartment livingApartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new RuntimeException("Apartment with ID " + apartmentId + " not found"));
        for (Resident x : residents) {
            if (HouseholdRole.OWNER.equals(x.getHouseholdRole())) {
                livingApartment.setOwner(x);
                livingApartment = apartmentRepository.save(livingApartment);
            }
            x.setLivingApartment(livingApartment);
        }
        residents = residentRepository.saveAll(residents);


        List<ResidentChangeLog> changeLogs = new ArrayList<>();
        for (Resident resident : residents) {
            ResidentChangeLog changeLog = new ResidentChangeLog();
            changeLog.setResident(residentMapper.entityToResponse(resident));
            changeLog.setApartment(apartmentMapper.entityToResponse(resident.getLivingApartment()));
            changeLog.setLastType(LivingType.O_NGOAI);
            changeLog.setChangeType(resident.getCurrentLivingType());
            changeLog.setChangeDate(new Date());
            changeLog.setNotes("Anh cho thang nay vao nha");
            changeLogs.add(changeLog);
        }
        residentChangeLogRepository.saveAll(changeLogs);


        return residentMapper.entityListToResponseList(residents);
    }


    @Override
    public Page<ResidentResponse> search(SearchRequest request) {
        Page<ResidentResponse> page = searchRepository.search(request, ResidentResponse.class);
        List<Long> apartmentIds = page.stream().map(ResidentResponse::getApartmentId).toList();
        Map<Long,Apartment> apartments = apartmentRepository.findAllById(apartmentIds).stream().collect(Collectors.toMap(Apartment::getApartmentId, x -> x));

        for (ResidentResponse x : page) {
            if (x.getApartmentId() != null) {
                x.setApartment(toApartmentResponse(apartments.get(x.getApartmentId())));
            }
        }
        return page;
    }
    private ApartmentResponse toApartmentResponse(Apartment apartment) {
        if (apartment == null) return null;
        return ApartmentResponse.builder().apartmentId(apartment.getApartmentId()).name(apartment.getName()).code(apartment.getCode()).build();
    }

    @Override
    public Page<ResidentChangeLogResponse> searchChangeLog(SearchRequest request) {
        return searchChangeLogRepository.search(request, ResidentChangeLogResponse.class);
    }

    @Override
    @Transactional
    public ResidentResponse removeFromApartment(Long id) {
        Resident resident = residentRepository.findById(id).orElseThrow(() -> new RuntimeException("Resident with ID " + id + " not found"));
        Apartment apartment = resident.getLivingApartment();
        if(apartment.getOwner()!=null && apartment.getOwner().getResidentId().equals(id)) {
           apartment.setOwner(null);
        }
        apartment = apartmentRepository.save(apartment);
        if(resident.getLivingApartment() == null) return residentMapper.entityToResponse(resident);
        ResidentChangeLog changeLog = new ResidentChangeLog();
        changeLog.setResident(residentMapper.entityToResponse(resident));
        changeLog.setResidentId(id);
        changeLog.setApartment(apartmentMapper.entityToResponse(apartment));
        changeLog.setApartmentId(changeLog.getApartment().getApartmentId());
        changeLog.setLastType(resident.getCurrentLivingType());
        changeLog.setChangeType(LivingType.O_NGOAI);
        changeLog.setChangeDate(new Date());
        changeLog.setNotes("Anh cho thang nay ra nha");

        residentChangeLogRepository.save(changeLog);
        resident.setLivingApartment(null);
        resident.setCurrentLivingType(LivingType.O_NGOAI);
        resident = residentRepository.save(resident);

        return residentMapper.entityToResponse(resident);
    }
}
