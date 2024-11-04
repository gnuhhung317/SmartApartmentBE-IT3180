package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.Role;
import com.hust.smart_apartment.dto.request.LoginRequest;
import com.hust.smart_apartment.dto.request.RegisterRequest;
import com.hust.smart_apartment.entity.User;
import com.hust.smart_apartment.repository.UserRepository;
import com.hust.smart_apartment.security.jwt.JWTProvider;
import com.hust.smart_apartment.service.AuthService;
import com.hust.smart_apartment.service.MailService;
import com.hust.smart_apartment.utils.StringUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final MailService mailService;

    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    private Set<String> users = new HashSet<>();

    @PostConstruct
    public void beforeConstruct() {
        users.addAll(userRepository.findAllUserName());
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        log.info("register user {}", registerRequest);

        String password;
        String potentialUsername = StringUtil.convertFullNameToUsername(registerRequest.getFullName());
        if (potentialUsername == null) {
            return "Họ và tên không hợp lệ";
        }
        StringBuilder usernameBuilder = new StringBuilder(potentialUsername);
        int suffix = 1;
        int length = usernameBuilder.length();
        usernameBuilder.append(suffix);

        while (users.contains(usernameBuilder.toString())) {
            suffix++;
            usernameBuilder.setLength(length);
            usernameBuilder.append(suffix);
        }

        password = registerRequest.getPassword();
        String username = usernameBuilder.toString();
        User user = new User()
                .setEmail(registerRequest.getEmail())
                .setFullName(registerRequest.getFullName())
                .setRole(Role.ROLE_USER)
                .setPassword(password)
                .setResidentId(registerRequest.getResidentId())
                .setUsername(username);

        mailService.sendRegisterVerifiedMail(user.getEmail(), jwtProvider.generateToken(user));
        userRepository.save(user);
        log.info("saved user {}", user);
        users.add(username);
        return "Đăng ký thành công!";
    }

    @Override
    public String login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticateToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticateToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        log.info("login user {}", user);

        return jwtProvider.generateToken(user);
    }

    @Override
    public void logout() {

        String s = "123";
        log.info(s);
    }

    @Override
    public String verifyRegister(String token) {
        if(jwtProvider.validateToken(token)) {
            String username = ((User) jwtProvider.getAuthentication(token).getPrincipal()).getUsername();
            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
            user.setIsVerified(true);
            userRepository.save(user);
            return "Xác minh thành công!";
        }
        return "";
    }
}
