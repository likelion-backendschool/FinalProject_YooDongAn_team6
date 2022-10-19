package com.example.mutbooks.post;

import com.example.mutbooks.post.dto.PostFormDto;
import com.example.mutbooks.postHashTag.PostHashTag;
import com.example.mutbooks.postHashTag.PostHashTagService;
import com.example.mutbooks.postKeyword.PostKeyword;
import com.example.mutbooks.postKeyword.PostKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostKeywordService postKeywordService;
    private final PostHashTagService postHashTagService;

    @GetMapping("/write")   // 글 등록 폼
    public String showPostForm(Model model, Principal principal) {
        model.addAttribute("postFormDto", new PostFormDto());
        String name = principal.getName();
        model.addAttribute("name", name);
        return "post/post_form";
    }

    @PostMapping("/write")  // 글 등록
    public String save(@ModelAttribute PostFormDto postFormDto) {
        postService.save(postFormDto);
        return "redirect:/post/list";
    }

    /* 글 수정 */
    @GetMapping("/{id}/modify")
    public String showModifyForm(@PathVariable("id") Long postId, Model model) {
        Post post = postService.findById(postId);
        PostModifyFormDto postModifyFormDto = postService.getPostModifyFormDto(post);
        model.addAttribute("postModifyFormDto", postModifyFormDto);
        return "post/modify_form";
    }

    @PostMapping("/{id}/modify")
    public String modify(@PathVariable("id") Long postId, @ModelAttribute PostModifyFormDto postModifyFormDto) {
        Post post = postService.findById(postId);
        postService.modifyPost(post, postModifyFormDto);

        return "redirect:/post/list";
    }
    // 전체 리스팅.
    @GetMapping("/list")
    public String showList(Model model) {
        List<Post> postList = postService.findAll();
        List<PostKeyword> postKeywords = postKeywordService.findAll();
        model.addAttribute("postList", postList);
        model.addAttribute("postKeywords", postKeywords);
        return "post/post_list";
    }
    // 해시태그 리스팅.
    @GetMapping("/{postKeywordId}/list")
    public String showList(Model model, @PathVariable Long postKeywordId) {
        PostKeyword postKeyword = postKeywordService.findById(postKeywordId);
        List<PostKeyword> postKeywords = postKeywordService.findAll();
        List<PostHashTag> postHashTags = postHashTagService.findAllByPostKeyword(postKeyword);
        List<Post> postListByHashTag = new ArrayList<>();
        for(PostHashTag postHashTag : postHashTags) {
            postListByHashTag.add(postHashTag.getPost());
        }
        model.addAttribute("postKeywords", postKeywords);
        model.addAttribute("postListByHashTag", postListByHashTag);

        return "post/post_list_hashTag";
    }

    /* 글 상세 */
    @GetMapping("/{id}")
    public String showPostDetail(@PathVariable("id") Long postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    /* 글 삭제 */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long postId) {
        Post post = postService.findById(postId);
        postHashTagService.deleteAllByPost(post);
        postService.deleteById(postId);
        return "post/list";
    }

}
