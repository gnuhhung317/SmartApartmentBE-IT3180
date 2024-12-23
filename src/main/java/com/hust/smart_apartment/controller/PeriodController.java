package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.PeriodRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.PeriodResponse;
import com.hust.smart_apartment.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/periods")
@RequiredArgsConstructor
public class PeriodController {
    private final PeriodService periodService;


    @GetMapping("/{id}")
    public BaseResponse<PeriodResponse> getById(@PathVariable Long id) {
        return BaseResponse.ok(periodService.getById(id));
    }

    @PostMapping
    public BaseResponse<PeriodResponse> create(@RequestBody PeriodRequest request) {
        return BaseResponse.ok(periodService.create(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<PeriodResponse> update(@PathVariable Long id, @RequestBody PeriodRequest request) {
        return BaseResponse.ok(periodService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ModifyDto> delete(@PathVariable Long id) {
        return BaseResponse.ok(periodService.delete(id));
    }

    @PostMapping("/search")
    public BaseResponse<Page<PeriodResponse>> search(@RequestBody SearchRequest request) {
        return BaseResponse.ok(periodService.search(request));
    }
}
