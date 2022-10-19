package com.example.mutbooks.postKeyword;

import javassist.compiler.ast.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostKeywordService {
    private final PostKeywordRepository postKeywordRepository;

    public PostKeyword save(String keywordContent) {
        Optional<PostKeyword> oPostKeyword = postKeywordRepository.findByContent(keywordContent);

        if ( oPostKeyword.isPresent() ) {
            return oPostKeyword.get();
        }

        PostKeyword postKeyword = PostKeyword
                .builder()
                .content(keywordContent)
                .build();

        postKeywordRepository.save(postKeyword);

        return postKeyword;
    }

    public PostKeyword findByContent(String postKeyword) {
        Optional<PostKeyword> oPostKeyword = postKeywordRepository.findByContent(postKeyword);
        return oPostKeyword.orElse(null);
    }

    public PostKeyword findById(Long postKeywordId) {
        Optional<PostKeyword> oPostKeyword = postKeywordRepository.findById(postKeywordId);
        return oPostKeyword.orElse(null);
    }

    public List<PostKeyword> findAll() {
        return postKeywordRepository.findAll();
    }
}
