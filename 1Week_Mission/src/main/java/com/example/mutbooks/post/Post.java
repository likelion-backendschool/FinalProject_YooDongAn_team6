package com.example.mutbooks.post;

import com.example.mutbooks.base.BaseEntity;
import com.example.mutbooks.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POST_Id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private Member member;

    @Column
    private String subject;

    @Column
    private String content;    // 마크다운

    @Lob
    private String contentHtml;    // HTML, 랜더링 결과

    public void changePost(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }


}
