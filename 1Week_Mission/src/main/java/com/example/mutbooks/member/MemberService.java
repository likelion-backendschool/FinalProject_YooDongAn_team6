package com.example.mutbooks.member;

import com.example.mutbooks.member.dto.SignupFormDto;
import com.example.mutbooks.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public void join(SignupFormDto signupFormDto) {
        Member member = Member.builder()
                .username(signupFormDto.getUsername())
                .password(passwordEncoder.encode(signupFormDto.getPassword1()))
                .createDate(LocalDateTime.now())
                .nickname(signupFormDto.getNickname())
                .email(signupFormDto.getEmail())
                .memberType(signupFormDto.presentNickname())
                .authLevel(signupFormDto.getAuthLevel())
                .build();
        memberRepository.save(member);
    }

    public Member findByUsername(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new MemberNotFoundException("해당 유저는 존재하지 않습니다."));
        return member;

    }
}
