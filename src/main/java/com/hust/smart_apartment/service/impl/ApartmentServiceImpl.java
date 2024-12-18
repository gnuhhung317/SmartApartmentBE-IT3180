package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.ApartmentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.mapper.ApartmentMapper;
import com.hust.smart_apartment.mapper.ResidentMapper;
import com.hust.smart_apartment.repository.ApartmentRepository;
import com.hust.smart_apartment.repository.ResidentRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.ApartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ResidentRepository residentRepository;
    private final ApartmentMapper apartmentMapper;
    private final ResidentMapper residentMapper;
    private final SearchRepository<ApartmentResponse> searchRepository;
    @Override
    public ApartmentResponse getById(Long id) {
        // Fetch apartment by ID or throw an exception if not found
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + id));
        return apartmentMapper.entityToResponse(apartment);
    }

    @Override
    public ApartmentResponse create(ApartmentRequest request) {
        // Map request DTO to entity, save to DB, and map the result back to response DTO
        Apartment apartment = apartmentMapper.requestToEntity(request);
        Apartment savedApartment = apartmentRepository.save(apartment);
        return apartmentMapper.entityToResponse(savedApartment);
    }

    @Override
    public ApartmentResponse update(Long id, ApartmentRequest request) {
        // Fetch apartment by ID, update fields, and save the updated entity
        Apartment existingApartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + id));

        // Update fields (assuming mapper can do this with a helper method)
        existingApartment.setCode(request.getCode());
        existingApartment.setName(request.getName());
        existingApartment.setArea(request.getArea());
        Apartment updatedApartment = apartmentRepository.save(existingApartment);

        return apartmentMapper.entityToResponse(updatedApartment);
    }

    @Override
    public ModifyDto delete(Long id) {
        // Fetch apartment by ID, delete it, and return success status
        apartmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + id));

        return new ModifyDto(apartmentRepository.deleteApartmentByApartmentId(id));
    }

    @Override
    public Page<ApartmentResponse> search(SearchRequest request) {
        return searchRepository.search(request, ApartmentResponse.class);
    }
}
