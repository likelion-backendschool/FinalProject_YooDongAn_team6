package com.example.mutbooks.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void sendMail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("회원가입을 축하드립니다!");
        simpleMailMessage.setText("회원님의 멋북스 회원가입을 축하드립니다! 항상 더 나은 서비스로 보답하겠습니다. 감사합니다.");

        javaMailSender.send(simpleMailMessage);
    }

}
