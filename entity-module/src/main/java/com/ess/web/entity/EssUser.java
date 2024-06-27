package com.ess.web.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EssUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long userId;

	private String empId;

	private String email;

	private String firstName;

	private String lastName;

	private Date lastLoginDate;

	private String password;

	private String loginName;

}
