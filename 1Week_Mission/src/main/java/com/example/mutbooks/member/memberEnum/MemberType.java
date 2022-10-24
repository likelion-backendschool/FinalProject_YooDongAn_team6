package com.example.mutbooks.member.memberEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {
    GENERAL("일반"),
    AUTHOR("작가");

    private final String value;
}