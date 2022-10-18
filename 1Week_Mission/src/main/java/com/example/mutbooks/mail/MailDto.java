package com.example.mutbooks.mail;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    private String address;
    @Builder.Default
    private String title = "멋북스 회원가입을 축하드립니다!";
    @Builder.Default
    private String message = "멋북스 서비스에 가입해주셔서 감사합니다!. 항상 더 나은 서비스를 제공할 수 있도록 노력하겠습니다.";
}