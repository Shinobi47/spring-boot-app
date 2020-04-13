package com.benayed.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_PROFILE")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserProfileEntity {
	
	@Id
	@Column(name = "UPR_USER_ID")
	private Integer id;
	
	@Column(name = "UPR_USER_NAME")
	private String username;
	
	@Column(name = "UPR_USER_PASSWD")
	private String password;

	@Column(name = "UPR_IS_USER_ACTIVE")
	private Boolean isActive;
}