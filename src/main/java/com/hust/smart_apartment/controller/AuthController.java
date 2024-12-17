package com.hust.smart_apartment.controller;


import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.request.LoginRequest;
import com.hust.smart_apartment.dto.request.RegisterRequest;
import com.hust.smart_apartment.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody RegisterRequest registerRequest) {
        return BaseResponse.ok(authService.register(registerRequest));
    }
    @GetMapping("/verify")
    public BaseResponse<String> verifyRegister(@RequestParam String token) {
        return BaseResponse.ok(authService.verifyRegister(token));
    }

    @PostMapping("/login")
    public BaseResponse<String> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        return BaseResponse.ok(authService.login(loginRequest));
    }
    @GetMapping()
    public BaseResponse<List<LoginRequest>> getAllUser() {
        return BaseResponse.ok(authService.getAllUser());
    }
}
