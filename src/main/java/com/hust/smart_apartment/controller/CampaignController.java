package com.hust.smart_apartment.controller;


import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.CampaignRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.CampaignResponse;
import com.hust.smart_apartment.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    @Qualifier("campaignServiceImpl")
    private final CampaignService campageService;


    @GetMapping("/{id}")
    public BaseResponse<CampaignResponse> getById(@PathVariable Long id) {
        return BaseResponse.ok(campageService.getById(id));
    }

    @PostMapping
    public BaseResponse<CampaignResponse> create(@RequestBody CampaignRequest request) {
        return BaseResponse.ok(campageService.create(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<CampaignResponse> update(@PathVariable Long id, @RequestBody CampaignRequest request) {
        return BaseResponse.ok(campageService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ModifyDto> delete(@PathVariable Long id) {
        return BaseResponse.ok(campageService.delete(id));
    }

    @PostMapping("/search")
    public BaseResponse<Page<CampaignResponse>> search(@RequestBody SearchRequest request) {
        return BaseResponse.ok(campageService.search(request));
    }
}
