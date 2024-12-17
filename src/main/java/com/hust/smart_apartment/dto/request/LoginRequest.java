package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.annotations.CustomSize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequest {

    @CustomSize(min = 20)
    private String username;
    @CustomSize
    private String password;
}
