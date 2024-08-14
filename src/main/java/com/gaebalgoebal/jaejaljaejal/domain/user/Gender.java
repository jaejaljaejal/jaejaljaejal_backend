package com.gaebalgoebal.jaejaljaejal.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("male", "남성"),
    FEMALE("female", "여성");

    private final String key;
    private final String value;
}
