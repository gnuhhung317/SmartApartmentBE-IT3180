package com.hust.smart_apartment.constants;

import com.hust.smart_apartment.dto.response.CodeNameResponse;

public interface CodeNameProvider {
    int getCode();
    String getName();
    String getEnumName();
    default CodeNameResponse toCodeNameResponse() {
        return new CodeNameResponse(getCode(),getName(),getEnumName());
    }
}
