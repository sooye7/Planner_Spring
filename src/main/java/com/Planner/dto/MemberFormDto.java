package com.Planner.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message="이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message="아이디는 필수 입력 값입니다.")
    private String id;

    @NotEmpty(message="비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @Email(message="이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message="닉네임은 필수 입력 값입니다.")
    @Length(min=1, message = "닉네임은 1자 이상으로 입력해주세요.")
    private String nickNm;

}
