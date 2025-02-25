package com.Tablely.Tablely.waiting.domain;

import java.time.LocalDateTime;

import com.Tablely.Tablely.BaseEntity;
import com.Tablely.Tablely.restaurant.domain.Restaurant;
import com.Tablely.Tablely.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * code: redis의 키값을 위해 임의의 값을 지정
 */
@Entity
@NoArgsConstructor
@Getter
public class Waiting extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "waiting_id")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rastaurant_id")
	private Restaurant restaurant;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	private Integer headCount;
	private String code;
	@Enumerated(value = EnumType.STRING)
	private WaitingStatus status;

	@PrePersist
	public void generateUniqueKey() {
		// UUID 로 코드 만들기.
	}
}