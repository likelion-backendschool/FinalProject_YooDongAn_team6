package com.example.mutbooks.member;

import com.example.mutbooks.member.dto.SignupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    /* 로그인 페이지 */
    @GetMapping("/login")
    private String showLoginForm() {
        return "member/login_form";
    }

    /* 회원가입 페이지 */
    @GetMapping("/join")
    private String showSignupForm(Model model) {
        model.addAttribute("signupFormDto", new SignupFormDto());
        return "member/signup_form";
    }

    @PostMapping("/join")
    private String join(@Validated @ModelAttribute SignupFormDto signupFormDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/signup_form";
        }

        if (!signupFormDto.getPassword1().equals(signupFormDto.getPassword2())) {
            bindingResult.rejectValue("password2", "notSamePassword",
                    "패스워드가 서로 일치하지 않습니다!");
            return "member/signup_form";
        }

        try {
            memberService.join(signupFormDto);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("duplicatedUsername", "이미 등록된 아이디입니다");
            return "member/signup_form";
        } catch (Exception e) {
            return "member/signup_form";
        }

        return "redirect:/member/login";
    }



}
