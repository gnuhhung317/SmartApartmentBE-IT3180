package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.BuildingRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.BuildingResponse;
import com.hust.smart_apartment.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @GetMapping("/{id}")
    public BaseResponse<BuildingResponse> getById(@PathVariable Long id) {
        BuildingResponse response = buildingService.getById(id);
        return BaseResponse.ok(response);
    }

    @PostMapping
    public BaseResponse<BuildingResponse> create(@RequestBody BuildingRequest request) {
        BuildingResponse response = buildingService.create(request);
        return BaseResponse.ok(response);
    }

    @PutMapping("/{id}")
    public BaseResponse<BuildingResponse> update(@PathVariable Long id, @RequestBody BuildingRequest request) {
        BuildingResponse response = buildingService.update(id, request);
        return BaseResponse.ok(response);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ModifyDto> delete(@PathVariable Long id) {
        ModifyDto response = buildingService.delete(id);
        return BaseResponse.ok(response);
    }

    @PostMapping("/search")
    public BaseResponse<Page<BuildingResponse>> search(@RequestBody SearchRequest request) {
        Page<BuildingResponse> response = buildingService.search(request);
        return BaseResponse.ok(response);
    }
}
