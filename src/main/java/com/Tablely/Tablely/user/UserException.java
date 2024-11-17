package com.Tablely.Tablely.user;

import com.Tablely.Tablely.global.exception.BusinessException;
import com.Tablely.Tablely.global.exception.ErrorCode;

public class UserException extends BusinessException {
	public UserException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

	public UserException(ErrorCode errorCode) {
		super(errorCode, errorCode.getDescription());
	}
}
