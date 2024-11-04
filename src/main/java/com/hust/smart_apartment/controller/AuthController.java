package com.hust.smart_apartment.controller;


import com.hust.smart_apartment.dto.request.LoginRequest;
import com.hust.smart_apartment.dto.request.RegisterRequest;
import com.hust.smart_apartment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
    @GetMapping("/verify")
    public String verifyRegister(@RequestParam String token) {
        return authService.verifyRegister(token);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
