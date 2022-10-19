package com.example.mutbooks.postHashTag;

import com.example.mutbooks.post.Post;
import com.example.mutbooks.postKeyword.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {
    Optional<PostHashTag> findByPostAndPostKeyword(Post post, PostKeyword postKeyword);

    List<PostHashTag> findByPost(Post post);

    List<PostHashTag> findAllByPostKeyword(PostKeyword postKeyword);
}
