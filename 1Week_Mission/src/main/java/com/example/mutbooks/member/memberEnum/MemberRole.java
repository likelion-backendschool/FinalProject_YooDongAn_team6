package com.example.mutbooks.member.memberEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
    GENERAL("일반"),
    ADMIN("관리자");

    private final String value;
}
