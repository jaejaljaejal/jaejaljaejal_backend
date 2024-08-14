package com.gaebalgoebal.jaejaljaejal.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserState {
    ACTIVE("active", "활성"),
    DISABLED("disabled", "비활성"),
    DELETE("delete", "삭제");

    private final String key;
    private final String value;
}