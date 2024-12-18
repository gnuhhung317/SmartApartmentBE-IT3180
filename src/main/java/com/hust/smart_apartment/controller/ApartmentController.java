package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.ApartmentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import com.hust.smart_apartment.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/apartments")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    @GetMapping("/{id}")
    public BaseResponse<ApartmentResponse> getById(@PathVariable Long id) {
        ApartmentResponse response = apartmentService.getById(id);
        return BaseResponse.ok(response);
    }

    @PostMapping
    public BaseResponse<ApartmentResponse> create(@RequestBody ApartmentRequest request) {
        ApartmentResponse response = apartmentService.create(request);
        return BaseResponse.ok(response);
    }

    @PutMapping("/{id}")
    public BaseResponse<ApartmentResponse> update(@PathVariable Long id, @RequestBody ApartmentRequest request) {
        ApartmentResponse response = apartmentService.update(id, request);
        return BaseResponse.ok(response);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ModifyDto> delete(@PathVariable Long id) {
        ModifyDto response = apartmentService.delete(id);
        return BaseResponse.ok(response);
    }

    @PostMapping("/search")
    public BaseResponse<Page<ApartmentResponse>> search(@RequestBody SearchRequest request) {
        Page<ApartmentResponse> response = apartmentService.search(request);
        return BaseResponse.ok(response);
    }
}

