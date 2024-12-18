package com.hust.smart_apartment.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequest {

    private String username;
    private String password;
}
