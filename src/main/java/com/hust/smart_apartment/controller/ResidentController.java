package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.ChangeLivingTypeRequest;
import com.hust.smart_apartment.dto.request.ResidentRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.ResidentChangeLogResponse;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.service.ResidentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/residents")
@RequiredArgsConstructor
public class ResidentController {
    private final ResidentService residentService;

    @PostMapping("change-living-type")
    public BaseResponse<ResidentChangeLogResponse> changeLivingType(@RequestParam("residentId") Long id, @RequestBody ChangeLivingTypeRequest request) {
        return BaseResponse.ok(residentService.changeLivingType(id, request));
    }

    @PutMapping("change-logs/{changeLogId}")
    public BaseResponse<ResidentChangeLogResponse> updateLivingType(@PathVariable("changeLogId") Long changeLogId, @RequestBody ChangeLivingTypeRequest request) {
        return BaseResponse.ok(residentService.updateLivingType(changeLogId, request));
    }

    @DeleteMapping("change-logs/{changeLogId}")
    public BaseResponse<ModifyDto> deleteLivingType(@PathVariable("changeLogId") Long changeLogId) {
        return BaseResponse.ok(residentService.deleteLivingType(changeLogId));
    }

    @GetMapping("/{id}")
    public BaseResponse<ResidentResponse> getResidentById(@PathVariable("id") Long id) {
        return BaseResponse.ok(residentService.getById(id));
    }

    @PostMapping("/to-apartment/{apartmentId}")
    public BaseResponse<List<ResidentResponse>> addResidentToApartment(@RequestBody List<ResidentRequest> request, @PathVariable("apartmentId") Long apartmentId) {
        return BaseResponse.ok(residentService.insertToApartment(request, apartmentId));
    }

    @PutMapping("/{id}")
    public BaseResponse<ResidentResponse> updateResident(@PathVariable("id") Long id, @RequestBody ResidentRequest request) {
        return BaseResponse.ok(residentService.update(id, request));
    }

    @PostMapping("/search")
    public BaseResponse<Page<ResidentResponse>> searchResidents(@RequestBody SearchRequest searchRequest) {
        return BaseResponse.ok(residentService.search(searchRequest));
    }
    @PostMapping("/change-log/search")
    public BaseResponse<Page<ResidentChangeLogResponse>> searchChangeLog(@RequestBody SearchRequest searchRequest) {
        return BaseResponse.ok(residentService.searchChangeLog(searchRequest));
    }

    @GetMapping("/remove-from-apartment/{id}")
    public BaseResponse<ResidentResponse> removeResidentFromApartment(@PathVariable("id") Long id) {
        return BaseResponse.ok(residentService.removeFromApartment(id));
    }
}
