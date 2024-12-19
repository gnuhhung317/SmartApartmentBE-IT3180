package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.FeeTypeRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.FeeTypeResponse;
import com.hust.smart_apartment.service.FeeTypeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fee-types")
@RequiredArgsConstructor
public class FeeTypeController {
    private final FeeTypeService feeTypeService;

    @GetMapping("/{id}")
    public BaseResponse<FeeTypeResponse> getById(@PathVariable Long id) {
        return BaseResponse.ok(feeTypeService.getById(id));
    }

    @PostMapping
    public BaseResponse<FeeTypeResponse> create(@RequestBody FeeTypeRequest request) {
        return BaseResponse.ok(feeTypeService.create(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<FeeTypeResponse> update(@PathVariable Long id, @RequestBody FeeTypeRequest request) {
        return BaseResponse.ok(feeTypeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ModifyDto> delete(@PathVariable Long id) {
        return BaseResponse.ok(feeTypeService.delete(id));
    }

    @PostMapping("/search")
    public BaseResponse<Page<FeeTypeResponse>> search(@RequestBody SearchRequest request) {
        return BaseResponse.ok(feeTypeService.search(request));
    }



}
