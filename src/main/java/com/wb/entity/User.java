package com.wb.entity;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wb.constant.Country;
import com.wb.constant.UserStatus;
import com.wb.constant.UserType;
import com.wb.request.S3RequestData;

@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "email", unique = true, columnDefinition = "VARCHAR(45) NOT NULL")
	private String email;

	@Column(name = "password", columnDefinition = "VARCHAR(45) NOT NULL")
	private String password;

	@Column(name = "user_name", unique = true, columnDefinition = "VARCHAR(45) NOT NULL")
	private String userName;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "VARCHAR(25) NOT NULL")
	private UserType type;

	@Enumerated(EnumType.STRING)
	@Column(name = "country", columnDefinition = "VARCHAR(45) NOT NULL")
	private Country country;

	@Column(name = "phone", columnDefinition = "VARCHAR(25)")
	private String phone;

	@Column(name = "first_name", columnDefinition = "VARCHAR(45) NOT NULL")
	private String firstName;

	@Column(name = "middle_name", columnDefinition = "VARCHAR(45)")
	private String middleName;

	@Column(name = "last_name", columnDefinition = "VARCHAR(45)")
	private String lastName;

	@Column(name = "qualification", columnDefinition = "VARCHAR(45)")
	private String qualification;

	@Column(name = "profile_image", columnDefinition = "VARCHAR(150)")
	private String profileImage;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "VARCHAR(25) NOT NULL DEFAULT 'PENDING'")
	private UserStatus status;

	// optional and only for writer
	@Column(name = "linkedin_url", columnDefinition = "VARCHAR(255)")
	private String linkedinUrl;

	@Column(name = "s3_request_data", columnDefinition = "text")
	private String s3RequestDataStr;

	@Transient
	private S3RequestData s3RequestData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
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

	public void setS3RequestDataStr(String s3RequestData) {
		this.s3RequestDataStr = s3RequestData;
	}

}
