package com.Tablely.Tablely.restaurant.domain;

import com.Tablely.Tablely.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Restaurant extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurent_id")
	private Long id;
	private String name;
	@Enumerated(value = EnumType.STRING)
	private Category category;
	private String description;
	private Integer maxWaitingCount;

	public Restaurant(Long id, String name, Category category, String description, Integer maxWaitingCount) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.maxWaitingCount = maxWaitingCount;
	}
}