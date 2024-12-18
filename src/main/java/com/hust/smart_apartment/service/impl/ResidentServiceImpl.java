package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.request.ChangeLivingTypeRequest;
import com.hust.smart_apartment.dto.response.ResidentChangeLogResponse;
import com.hust.smart_apartment.entity.Resident;
import com.hust.smart_apartment.entity.ResidentChangeLog;
import com.hust.smart_apartment.mapper.ResidentChangeLogMapper;
import com.hust.smart_apartment.repository.ResidentChangeLogRepository;
import com.hust.smart_apartment.repository.ResidentRepository;
import com.hust.smart_apartment.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentChangeLogRepository residentChangeLogRepository;
    private final ResidentChangeLogMapper changeLogMapper;

    @Override
    public ResidentChangeLogResponse changeLivingType(Long residentId, ChangeLivingTypeRequest request) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new RuntimeException("Resident with ID " + residentId + " not found"));

        ResidentChangeLog changeLog = new ResidentChangeLog();
        changeLog.setResident(resident);
        changeLog.setApartment(resident.getLivingApartment());
        changeLog.setLastType(resident.getCurrentLivingType());
        changeLog.setChangeType(request.getNewLivingType());
        changeLog.setChangeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        changeLog.setNotes(request.getNotes());

        return changeLogMapper.entityToResponse(residentChangeLogRepository.save(changeLog));
    }
}
