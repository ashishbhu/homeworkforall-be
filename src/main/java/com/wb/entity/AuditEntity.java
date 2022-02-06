package com.wb.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;

	@PrePersist
	protected void onCreate() {
		LocalDateTime currentDate = LocalDateTime.now();
		this.createDate = currentDate;
		this.modifiedDate = currentDate;
	}

	@PreUpdate
	protected void onUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

}
