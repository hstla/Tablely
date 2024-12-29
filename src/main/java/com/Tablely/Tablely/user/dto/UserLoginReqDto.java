package com.Tablely.Tablely.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginReqDto {
	@Email
	private String email;
	@NotNull
	private String password;

	public UserLoginReqDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
