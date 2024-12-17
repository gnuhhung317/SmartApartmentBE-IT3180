package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final String FROM = "mailtrap@demomailtrap.com";

    private final String VERIFY_URL = "http://localhost:8080/api/v1/auth";

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(FROM);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendRegisterVerifiedMail(String to, String token) {
        String subject = "Hãy xác minh tài khoản chung cư Blue Moon của bạn";
        String content = "Chào con vợ,<br>"
                + "Chú là người mới hả?<br>"
                + "Muốn xác minh tài khoản chung cư Blue Moon thì ấn vào đây cho anh:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Cảm ơn chú,<br>"
                + "Bố đẻ của Blue Moon,<br>"
                + "Hùng và 4 con lợn.";
        String verifyURL = VERIFY_URL + "/verify?token=" + token;
        content = content.replace("[[URL]]", verifyURL);

        send(to, subject, content);
    }
}
