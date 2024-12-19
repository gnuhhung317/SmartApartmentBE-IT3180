package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.request.VehicleRequest;
import com.hust.smart_apartment.dto.response.VehicleResponse;
import com.hust.smart_apartment.dto.response.VehicleTypeResponse;
import com.hust.smart_apartment.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/search")
    public BaseResponse<Page<VehicleResponse>> searchVehicles(@RequestBody SearchRequest searchRequest) {
        Page<VehicleResponse> result = vehicleService.search(searchRequest);
        return  BaseResponse.ok(result);
    }

    @PostMapping
    public BaseResponse<VehicleResponse> createVehicle(@RequestBody VehicleRequest vehicleRequest) {
        VehicleResponse vehicleResponse = vehicleService.create(vehicleRequest);
        return BaseResponse.ok(vehicleResponse);
    }

    @GetMapping("/{id}")
    public BaseResponse<VehicleResponse> getVehicleById(@PathVariable Long id) {
        VehicleResponse vehicleResponse = vehicleService.getById(id);
        return BaseResponse.ok(vehicleResponse);
    }

    @PutMapping("/{id}")
    public BaseResponse<VehicleResponse> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequest vehicleRequest) {
        VehicleResponse vehicleResponse = vehicleService.update(id, vehicleRequest);
        return BaseResponse.ok(vehicleResponse);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ModifyDto> deleteVehicle(@PathVariable Long id) {
        return BaseResponse.ok(vehicleService.delete(id));
    }

    @PostMapping("/vehicle-type/{id}")
    public BaseResponse<VehicleTypeResponse> updateVehicleType(@PathVariable Long id, @RequestBody Integer unitPrice) {
        VehicleTypeResponse vehicleTypeResponse = vehicleService.updateVehicleType(id, unitPrice);
        return BaseResponse.ok(vehicleTypeResponse);
    }
}
