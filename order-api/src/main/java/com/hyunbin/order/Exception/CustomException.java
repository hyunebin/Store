package com.hyunbin.order.Exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final int status;

    public CustomException(ErrorCode errorCode){
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
    }
}
