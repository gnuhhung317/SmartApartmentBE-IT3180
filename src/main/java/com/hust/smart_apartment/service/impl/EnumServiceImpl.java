package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.CodeNameProvider;
import com.hust.smart_apartment.dto.response.CodeNameResponse;
import com.hust.smart_apartment.service.EnumService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnumServiceImpl implements EnumService {
    @Override
    public <E extends Enum<E>& CodeNameProvider> List<CodeNameResponse> getEnum(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).map(x-> new CodeNameResponse(x.getCode(),x.getName(),x.name())).toList();
    }
}
