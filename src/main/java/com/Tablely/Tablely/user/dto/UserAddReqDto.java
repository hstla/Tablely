package com.Tablely.Tablely.user.dto;

import com.Tablely.Tablely.global.exception.BusinessException;
import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.UserException;
import com.Tablely.Tablely.user.domain.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAddReqDto {
	@NotNull
	private String name;
	@Email
	private String email;
	@NotNull
	private UserType userType;
	@NotNull
	private String password;
	@NotNull
	private String checkPassword;

	public UserAddReqDto(String name, String email, UserType userType, String password, String checkPassword) {
		this.name = name;
		this.email = email;
		this.userType = userType;
		this.password = password;
		this.checkPassword = checkPassword;
	}

	public boolean checkPassword() {
		if (!this.password.equals(this.checkPassword)) {
			throw new UserException(ErrorCode.INVALID_PASSWORD);
		}
		return true;
	}
}