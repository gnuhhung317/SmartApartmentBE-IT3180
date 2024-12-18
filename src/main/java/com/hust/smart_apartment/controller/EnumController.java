package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.constants.HouseholdRole;
import com.hust.smart_apartment.constants.LivingType;
import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.response.CodeNameResponse;
import com.hust.smart_apartment.service.EnumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enum")
@RequiredArgsConstructor
public class EnumController {
    private final EnumService enumService;

    @GetMapping("/household-role")
    public BaseResponse<List<CodeNameResponse>> getHouseholdRole() {
        return BaseResponse.ok(enumService.getEnum(HouseholdRole.class));
    }

    @GetMapping("/living-type")
    public BaseResponse<List<CodeNameResponse>> getLivingType() {
        return BaseResponse.ok(enumService.getEnum(LivingType.class));
    }
}
