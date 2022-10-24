package com.example.mutbooks.member;

import com.example.mutbooks.mail.MailService;
import com.example.mutbooks.member.dto.ModifyFormDto;
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
    private final MailService mailService;

    // 회원가입
    public Member join(SignupFormDto signupFormDto) {
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
        mailService.sendMail(signupFormDto.getEmail());
        return member;
    }

    public Member findByUsername(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new MemberNotFoundException("해당 유저는 존재하지 않습니다."));
        return member;

    }

    public void modify(ModifyFormDto modifyFormDto) {
        Member member = Member.builder()
                .username(modifyFormDto.getUsername())
                .password(passwordEncoder.encode(modifyFormDto.getPassword1()))
                .createDate(LocalDateTime.now())
                .nickname(modifyFormDto.getNickname())
                .email(modifyFormDto.getEmail())
                .memberType(modifyFormDto.presentNickname())
                .authLevel(modifyFormDto.getAuthLevel())
                .build();
        memberRepository.save(member);
    }

    public ModifyFormDto getModifyFormDtoByMember(Member member) {
        ModifyFormDto modifyFormDto = ModifyFormDto.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();
        return modifyFormDto;
    }

    /* 아이디 찾기 */
    public String findUsernameByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException("해당 유저는 존재하지 않습니다."));
        return member.getUsername();

    }
}
