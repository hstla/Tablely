package com.Tablely.Tablely.global.exception;

public enum ErrorCode {
	USER_NOT_FOUND("유저를 찾을 수 없습니다.", 401),
	JWT_INVALID_TOKEN("잘못된 토큰 값입니다.", 402),
	JWT_TOKEN_NOT_PROVIDED("토큰이 존재하지 않습니다.", 403),
	INVALID_PASSWORD("비밀번호가 일치하지 않습니다.", 404),
	USER_DUPLICATED("이메일이 중복되었습니다.", 405);


	private final String description;
	private final int status;

	ErrorCode(String description, int status) {
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public int getStatus() {
		return status;
	}
}
