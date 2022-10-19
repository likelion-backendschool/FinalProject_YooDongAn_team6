package com.example.mutbooks.postKeyword;

import com.example.mutbooks.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostKeyword extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POST_KEYWORD_Id")
    private Long id;

    @Column
    private String content;   // 해시태그

}
