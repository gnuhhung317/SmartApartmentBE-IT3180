package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.DonationRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.DonationResponse;
import com.hust.smart_apartment.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @GetMapping("/{id}")
    public BaseResponse<DonationResponse> getById(@PathVariable Long id) {
        return BaseResponse.ok(donationService.getById(id));
    }

    @PostMapping
    public BaseResponse<DonationResponse> create(@RequestBody DonationRequest request) {
        return BaseResponse.ok(donationService.create(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<DonationResponse> update(@PathVariable Long id, @RequestBody DonationRequest request) {
        return BaseResponse.ok(donationService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ModifyDto> delete(@PathVariable Long id) {
        return BaseResponse.ok(donationService.delete(id));
    }

    @PostMapping("/search")
    public BaseResponse<Page<DonationResponse>> search(@RequestBody SearchRequest request) {
        return BaseResponse.ok(donationService.search(request));
    }
}
