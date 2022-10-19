package com.example.mutbooks.post;

import com.example.mutbooks.post.dto.PostFormDto;
import com.example.mutbooks.post.exception.PostNotFoundException;
import com.example.mutbooks.postHashTag.PostHashTagService;
import com.example.mutbooks.postKeyword.PostKeyword;
import com.example.mutbooks.postKeyword.PostKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostHashTagService postHashTagService;
    private final PostKeywordService postKeywordService;

    public Post save(PostFormDto postFormDto) {
        String[] postKeywords = postFormDto.getPostKeyword().split("#");

        Post post = Post.builder()
            .subject(postFormDto.getSubject())
            .content(postFormDto.getContent())
                .build();

        postRepository.save(post);

        for(String postKeyword : postKeywords) {
            if(postKeyword.isEmpty()) continue;
            PostKeyword postKeyword1 = postKeywordService.save(postKeyword);
            postHashTagService.save(post, postKeyword1);
        }

        return post;
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("찾는 게시물이 없습니다!"));
    }

    public void modifyPost(Post post, PostModifyFormDto postModifyFormDto) {
        post.changePost(postModifyFormDto.getSubject(), postModifyFormDto.getContent());



    }

    // modify form 에 게시물 정보를 넣는다.
    public PostModifyFormDto getPostModifyFormDto(Post post) {
        postHashTagService.findByPost(post);

        PostModifyFormDto postModifyFormDto = PostModifyFormDto.builder()
                .subject(post.getSubject())
                .content(post.getContent())
                .build();


        return postModifyFormDto;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
