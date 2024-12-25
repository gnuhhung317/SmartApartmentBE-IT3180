package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.VehicleRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.VehicleResponse;
import com.hust.smart_apartment.dto.response.VehicleTypeResponse;
import com.hust.smart_apartment.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    VehicleResponse getById(Long id);
    VehicleResponse create(VehicleRequest request);
    VehicleResponse update(Long id,VehicleRequest request);
    ModifyDto delete(Long id);
    Page<VehicleResponse> search(SearchRequest request);
    VehicleTypeResponse updateVehicleType(Long id, Integer unitPrice);
    List<VehicleTypeResponse> getAll();
    List<VehicleTypeResponse> createVehicleTypes();
}
