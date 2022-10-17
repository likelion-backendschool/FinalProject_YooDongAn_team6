package com.example.mutbooks.config.security;

import com.example.mutbooks.member.Member;
import com.example.mutbooks.member.MemberService;
import com.example.mutbooks.member.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    /* 로그인을 하기 위해 가입된 유저 정보를 조회, 각 역할 별 권한 부여 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByUsername(username);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getAuthLevel() == 3) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.GENERAL.getValue()));
        } else if (member.getAuthLevel() == 7){
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }

        return new User(member.getUsername(), member.getPassword(), authorities);
    }

}