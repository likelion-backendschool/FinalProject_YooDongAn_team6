package com.example.mutbooks.postHashTag;
import com.example.mutbooks.post.Post;
import com.example.mutbooks.postKeyword.PostKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostHashTagService {
    private final PostHashTagRepository postHashTagRepository;

    public void save(Post post, PostKeyword postKeyword) {
        Optional<PostHashTag> oPostHashTag = postHashTagRepository.findByPostAndPostKeyword(post, postKeyword);
        if(oPostHashTag.isPresent()) return;
        PostHashTag postHashTag = PostHashTag.builder()
                .post(post)
                .postKeyword(postKeyword)
                .build();
        postHashTagRepository.save(postHashTag);

    }

    public List<PostHashTag> findByPost(Post post) {
        return postHashTagRepository.findByPost(post);
    }

    public List<PostHashTag> findAllByPostKeyword(PostKeyword postKeyword) {
        return postHashTagRepository.findAllByPostKeyword(postKeyword);
    }

    public void deleteAllByPost(Post post) {
        postHashTagRepository.deleteAllByPost(post);
    }
}
