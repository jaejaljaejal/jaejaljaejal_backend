package com.gaebalgoebal.jaejaljaejal.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginMothod {
    EMAIL("email", "이메일"),
    KAKAO("kakao", "카카오"),
    GOOGLE("google", "구글");

    private final String key;
    private final String value;
}
