package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.request.LoginRequest;
import com.hust.smart_apartment.dto.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);

    void logout();

    String verifyRegister(String token);


}
