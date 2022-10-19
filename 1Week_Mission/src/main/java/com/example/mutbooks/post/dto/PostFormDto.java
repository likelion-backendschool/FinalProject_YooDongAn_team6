package com.example.mutbooks.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostFormDto {

    private String subject;

    private String content;

    private String postKeyword; // 해시태그.
}
