package com.example.mutbooks.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

//    @PostMapping("/mail")
//    public void sendMail(String email) {
//        mailService.sendMail(email);
//    }

}
