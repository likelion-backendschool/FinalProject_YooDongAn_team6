package com.example.mutbooks.member;

import com.example.mutbooks.mail.MailService;
import com.example.mutbooks.member.dto.ModifyFormDto;
import com.example.mutbooks.member.dto.SignupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;

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
            Member member = memberService.join(signupFormDto);
//            mailService.sendMail(member.getEmail());
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("duplicatedUsername", "이미 등록된 아이디입니다");
            return "member/signup_form";
        } catch (Exception e) {
            return "member/signup_form";
        }

        return "redirect:/member/login";
    }

    /* 회원 정보 수정 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify")
    public String showModifyForm(Model model, @AuthenticationPrincipal User user) {
        String username = user.getUsername();
        Member member = memberService.findByUsername(username);
        ModifyFormDto modifyFormDto = memberService.getModifyFormDtoByMember(member);
        model.addAttribute("modifyFormDto", modifyFormDto);
        return "member/modify_form";
    }

    @PostMapping("/modify")
    public String modify(@Validated @ModelAttribute ModifyFormDto modifyFormDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/modify_form";
        }

        if (!modifyFormDto.getPassword1().equals(modifyFormDto.getPassword2())) {
            bindingResult.rejectValue("password2", "notSamePassword",
                    "패스워드가 서로 일치하지 않습니다!");
            return "member/modify_form";
        }

        try {
            memberService.modify(modifyFormDto);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("duplicatedUsername", "이미 등록된 아이디입니다");
            return "member/modify_form";
        } catch (Exception e) {
            return "member/modify_form";
        }

        return "redirect:/member/login";

    }

    /* 아이디 찾기 */
    @GetMapping("/findUsername")
    public String findUsername() {
        return "member/find_username";

    }
    @PostMapping("/findUsername")
    @ResponseBody
    public String findUsername(@RequestParam String email) {
        String usernameByEmail = memberService.findUsernameByEmail(email);
        return usernameByEmail;
    }
}
