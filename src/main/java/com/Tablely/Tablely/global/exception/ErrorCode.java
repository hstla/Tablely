package com.Tablely.Tablely.global.exception;

public enum ErrorCode {
	USER_NOT_FOUND("유저를 찾을 수 없습니다.", 401),
	JWT_INVALID_TOKEN("잘못된 토큰 값입니다.", 402);

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
