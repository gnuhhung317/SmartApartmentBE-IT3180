package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.ApartmentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.request.UpdateApartmentRequest;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.entity.FeeType;
import com.hust.smart_apartment.entity.Floor;
import com.hust.smart_apartment.entity.Resident;
import com.hust.smart_apartment.mapper.ApartmentMapper;
import com.hust.smart_apartment.mapper.ResidentMapper;
import com.hust.smart_apartment.repository.*;
import com.hust.smart_apartment.service.ApartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ResidentRepository residentRepository;
    private final ApartmentMapper apartmentMapper;
    private final ResidentMapper residentMapper;
    private final SearchRepository<ApartmentResponse> searchRepository;
    private final FloorRepository floorRepository;
    private final FeeTypeRepository feeTypeRepository;

    @Override
    public ApartmentResponse getById(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + id));
        return apartmentMapper.entityToResponse(apartment);
    }

    @Override
    public ApartmentResponse create(ApartmentRequest request) {

        Apartment apartment = apartmentMapper.requestToEntity(request);
        Floor floor = floorRepository.findById(request.getFloorId())
                .orElseThrow(() -> new EntityNotFoundException("Floor not found with ID: " + request.getFloorId()));
        FeeType serviceFee = feeTypeRepository.findById(request.getServiceFeeId()).orElseThrow(() -> new EntityNotFoundException("FeeType not found with ID: " + request.getServiceFeeId()));
        FeeType managementFee = feeTypeRepository.findById(request.getManagementFeeId()).orElseThrow(() -> new EntityNotFoundException("FeeType not found with ID: " + request.getManagementFeeId()));

        apartment.setServiceFee(serviceFee);
        apartment.setManagementFee(managementFee);
        apartment.setFloor(floor);
        return apartmentMapper.entityToResponse(apartmentRepository.save(apartment));
    }


    @Override
    public ApartmentResponse update(Long id, UpdateApartmentRequest request) {
        Apartment existingApartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + id));

        Resident owner = request.getOwner();
        List<Resident> residents = request.getResidents().stream().map(residentMapper::requestToEntity).toList();
        Set<Long> oldResidentIds = existingApartment.getResidents().stream().map(Resident::getResidentId).collect(Collectors.toSet());
        Set<Resident> createdResidentIds = residents.stream().filter(resident -> !oldResidentIds.contains(resident.getResidentId())).collect(Collectors.toSet());
        Set<Resident> updateResidentIds = residents.stream().filter(resident -> oldResidentIds.contains(resident.getResidentId())).collect(Collectors.toSet());
        List<Resident> removeResidentIds = existingApartment.getResidents().stream().filter(resident -> !updateResidentIds.contains(resident)).peek(x -> x.setLivingApartment(null)).toList();

        List<Resident> residentList = new ArrayList<>(residentRepository.saveAll(updateResidentIds));
        residentList.addAll(residentRepository.saveAll(createdResidentIds));
        residentList.addAll(residentRepository.saveAll(removeResidentIds));

//        List<ResidentChangeLog> logs = residentList.stream().map(resident -> {
//            ResidentChangeLog log = new ResidentChangeLog();
//            log.setApartment(apartmentMapper.entityToResponse(existingApartment));
//            log.setResident(residentMapper.entityToResponse(resident));
//            log.setChangeDate(LocalDateTime.now());
//            log.setNotes("Change owner");
//            if(createdResidentIds.contains(resident)) {
//                log.setLastType(LivingType.O_NGOAI);
//                log.setChangeType(resident.getCurrentLivingType());
//            }else {
//                log.setLastType(resident.getCurrentLivingType());
//                log.setChangeType(LivingType.O_NGOAI);
//            }
//        });

        existingApartment.setOwner(owner);
        existingApartment.setResidents(residents);
        Apartment updatedApartment = apartmentRepository.save(existingApartment);

        return apartmentMapper.entityToResponse(updatedApartment);
    }

    @Override
    public ModifyDto delete(Long id) {
        if (!apartmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Apartment not found with ID: " + id);
        }
        return new ModifyDto(apartmentRepository.deleteApartmentByApartmentId(id));
    }

    @Override
    public Page<ApartmentResponse> search(SearchRequest request) {
        return mappingResidents(searchRepository.search(request, ApartmentResponse.class));
    }

    private Page<ApartmentResponse> mappingResidents(Page<ApartmentResponse> responses) {
        List<Long> ownerIds = responses.stream().map(ApartmentResponse::getOwnerId).toList();
        List<Long> apartmentIds = responses.stream().map(ApartmentResponse::getApartmentId).toList();
        Map<Long, ResidentResponse> owners = residentRepository.findAllById(ownerIds).stream().collect(Collectors.toMap(Resident::getResidentId, residentMapper::entityToResponse));
        Map<Long, List<ResidentResponse>> residentResponses = apartmentRepository.findAllById(apartmentIds).stream().collect(Collectors.toMap(Apartment::getApartmentId, x -> residentMapper.entityListToResponseList(x.getResidents())));

        for (ApartmentResponse response : responses) {
            response.setOwner(owners.get(response.getOwnerId()));
            response.setResidents(residentResponses.get(response.getApartmentId()));
        }
        return responses;
    }
}
