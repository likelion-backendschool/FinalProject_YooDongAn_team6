package com.example.mutbooks.postHashTag;

import com.example.mutbooks.base.BaseEntity;
import com.example.mutbooks.post.Post;
import com.example.mutbooks.postKeyword.PostKeyword;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostHashTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_HASH_TAG_Id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_KEYWORD_ID")
    private PostKeyword postKeyword;

}