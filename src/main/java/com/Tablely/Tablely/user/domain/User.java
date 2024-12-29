package com.Tablely.Tablely.user.domain;

import com.Tablely.Tablely.BaseEntity;
import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.UserException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@NotNull
	private String email;
	@NotNull
	private String password;
	private String name;
	@Enumerated(EnumType.STRING)
	private UserType userType;

	public User(String email, String password, String name, UserType userType) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.userType = userType;
	}

	// 패스워드 확인
	public void checkPassword(String password) {
		if (!this.password.equals(password)) {
			throw new UserException(ErrorCode.WRONG_PASSWORD);
		}
	}
}