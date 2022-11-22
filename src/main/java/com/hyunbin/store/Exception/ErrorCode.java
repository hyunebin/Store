package com.hyunbin.store.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REG_USER(HttpStatus.BAD_REQUEST, "이미 가입된 유저 입니다.");

    private final HttpStatus httpStatus;
    private final String description;
}
