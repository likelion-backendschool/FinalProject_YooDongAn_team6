package com.example.mutbooks.post;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostModifyFormDto {
    private String subject;

    private String content;

    private String postKeyword; // 해시태그.
}
