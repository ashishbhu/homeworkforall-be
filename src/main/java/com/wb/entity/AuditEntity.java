package com.wb.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "create_date")
	private LocalDate createDate;

	@Column(name = "modified_date")
	private LocalDate modifiedDate;

	@PrePersist
	protected void onCreate() {
		LocalDate currentDate = LocalDate.now();
		this.createDate = currentDate;
		this.modifiedDate = currentDate;
	}

	@PreUpdate
	protected void onUpdate() {
		this.modifiedDate = LocalDate.now();
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

}
