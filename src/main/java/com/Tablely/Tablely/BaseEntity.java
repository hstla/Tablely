package com.Tablely.Tablely;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * MappedSuperclass: 공통 슈퍼클래스 - 혼자서는 DB에 매핑되지 않지만 상속받는 자식 클래스에 필드로 제공된다.
 * EntityListeners: JPA(Entity)를 대상으로 특정 이벤트가 발생할 때 리스너 클래스를 통해 해당 이벤트를 처리할 수 있게 해주는 어노테이션
 * AuditingEntityListener.class: JPA 엔티티의 생성 및 수정 시간을 자동으로 기록해 주는 리스너
 *
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	@LastModifiedDate
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;
}
