package com.hust.smart_apartment.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeNameResponse {
    private int code;
    private String name;
}
