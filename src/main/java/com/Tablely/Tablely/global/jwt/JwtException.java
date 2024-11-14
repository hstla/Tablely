package com.Tablely.Tablely.global.jwt;

import com.Tablely.Tablely.global.exception.BusinessException;
import com.Tablely.Tablely.global.exception.ErrorCode;

public class JwtException extends BusinessException {
    public JwtException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public JwtException(ErrorCode errorCode) {
        super(errorCode, errorCode.getDescription());
    }
}