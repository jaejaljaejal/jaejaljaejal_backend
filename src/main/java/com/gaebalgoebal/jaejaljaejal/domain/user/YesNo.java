package com.gaebalgoebal.jaejaljaejal.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum YesNo {
    YES("Y"),
    NO("N");

    private final String value;
}