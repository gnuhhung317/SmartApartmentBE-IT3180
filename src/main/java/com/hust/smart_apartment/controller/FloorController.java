package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.request.FloorRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.FloorResponse;
import com.hust.smart_apartment.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/floors")
@RequiredArgsConstructor
public class FloorController {
    private final FloorService floorService;

    @PostMapping
    public BaseResponse<FloorResponse> create(@RequestBody FloorRequest request) {
        FloorResponse response = floorService.create(request);
        return BaseResponse.ok(response);
    }

    @PostMapping("/search")
    public BaseResponse<Page<FloorResponse>> search(@RequestBody SearchRequest request) {
        Page<FloorResponse> response = floorService.search(request);
        return BaseResponse.ok(response);
    }
}
