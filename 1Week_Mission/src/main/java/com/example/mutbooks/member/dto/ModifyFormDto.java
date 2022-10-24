package com.example.mutbooks.member.dto;

import com.example.mutbooks.member.memberEnum.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class ModifyFormDto {
    @NotEmpty(message = "아이디 작성은 필수입니다.")
    private String username;
    @NotEmpty(message = "비밀번호 작성은 필수입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String password2;

    private LocalDateTime updateDate;

    @Nullable
    private String nickname;

    @NotEmpty(message = "이메일 작성은 필수입니다.")
    private String email;

    @Builder.Default
    private Long authLevel = 3L;

    public MemberType presentNickname() {
        if(this.nickname.isEmpty()) return MemberType.GENERAL;
        return MemberType.AUTHOR;
    }

}
