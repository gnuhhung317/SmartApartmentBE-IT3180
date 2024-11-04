package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface MailService {

    void send(String to, String subject, String content);

    void sendRegisterVerifiedMail(String to, String token);

}
