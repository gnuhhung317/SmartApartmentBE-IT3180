package com.hust.smart_apartment.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeGeneratorType {
    INVOICE("HĐ-");
    private final String prefix;
}
