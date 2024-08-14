package com.gaebalgoebal.jaejaljaejal.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gaebalgoebal.jaejaljaejal.domain.user.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserCreateDto {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotNull
    private String email;
    @NotNull
    private String nickname;
    @NotNull
    private String password;
    private String cellPhoneNumber;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate birthDate;
    @NotNull
    private String gender;
}
