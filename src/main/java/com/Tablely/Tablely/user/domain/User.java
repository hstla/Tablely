package com.Tablely.Tablely.user.domain;

import com.Tablely.Tablely.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
}