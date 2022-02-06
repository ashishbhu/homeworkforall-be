package com.wb.entity;

import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wb.request.S3RequestData;

@Entity
@Table(name = "portfolios")
@DynamicInsert
@DynamicUpdate
public class Portfolio extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "s3_request_data", columnDefinition = "text", nullable = false)
	private String s3RequestDataStr;

	@Transient
	private S3RequestData s3RequestData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setS3RequestDataStr(String s3RequestDataStr) {
		this.s3RequestDataStr = s3RequestDataStr;
	}

	public S3RequestData getS3RequestData() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (s3RequestDataStr != null) {
				return mapper.readValue(s3RequestDataStr, S3RequestData.class);
			}
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return null;
	}
}
