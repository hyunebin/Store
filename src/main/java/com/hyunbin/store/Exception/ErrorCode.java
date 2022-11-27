package com.hyunbin.store.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REG_USER(HttpStatus.BAD_REQUEST, "이미 가입된 유저 입니다."),
    NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "유저가 존재하지 않습니다."),
    NOT_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일 입니다."),

    NOT_ALLOW_EMAIL_AUTH(HttpStatus.BAD_REQUEST, "이메일 인증되지 않은 사용자 입니다."),

    ALREADY_ALLOW_USER(HttpStatus.BAD_REQUEST, "이미 인증된 사용자 입니다."),

    CODE_NOT_EQ(HttpStatus.BAD_REQUEST, "인증 코드가 일지하지 않습니다."),

    AUTHENTICATION_TIMEOUT(HttpStatus.BAD_REQUEST, "인증 시간이 만료되었습니다.");


    private final HttpStatus httpStatus;
    private final String description;
}
