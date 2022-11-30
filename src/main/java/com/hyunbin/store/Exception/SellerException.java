package com.hyunbin.store.Exception;


import lombok.Getter;

@Getter
public class SellerException extends RuntimeException{
    private final ErrorCode errorCode;

    public SellerException(ErrorCode errorCode){
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
