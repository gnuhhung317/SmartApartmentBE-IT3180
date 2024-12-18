package com.hust.smart_apartment.service;

import com.hust.smart_apartment.constants.CodeNameProvider;
import com.hust.smart_apartment.dto.response.CodeNameResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnumService {
    <E extends Enum<E>& CodeNameProvider> List<CodeNameResponse> getEnum(Class<E> enumClass);
}
