package com.ess.web.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class EssUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long userId;

	@Pattern(regexp = "^[a-zA-Z0-9_-]{1,20}$", message = "Employee ID must consist of alphanumeric characters, underscore, or hyphen, and be between 1 to 20 characters long")
	private String empId;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z-' ]{1,50}$", message = "Name must consist of letters, hyphens, and spaces only")
	private String UserName;

	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Password must contain at least one lowercase letter, one uppercase letter, one numeric digit, and one special character")
	private String password;

	private Date lastLoginDate;

}
