package com.gaebalgoebal.jaejaljaejal.domain.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class EmailDto {
    @Email
    private String email;
    private String verityCode;
}
