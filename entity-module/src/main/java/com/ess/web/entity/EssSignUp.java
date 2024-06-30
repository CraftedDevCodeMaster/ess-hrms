package com.ess.web.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class EssSignUp {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Pattern(regexp = "^[a-zA-Z0-9_-]{1,20}$", message = "Employee ID must consist of alphanumeric characters, underscore, or hyphen, and be between 1 to 20 characters long")
	private String empId;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z-' ]{1,50}$", message = "First name must consist of letters, hyphens, and spaces only")
	private String firstName;

	@Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z-' ]{1,50}$", message = "Last name must consist of letters, hyphens, and spaces only")
	private String lastName;

	@NotNull(message = "Create date cannot be null")
	private Date createDate;

	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Password must contain at least one lowercase letter, one uppercase letter, one numeric digit, and one special character")
	private String newPassword;

	@Transient
	@NotNull(message = "Confirm password cannot be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Confirm password must match password requirements")
	private String confirmPassword;

	@NotBlank(message = "Company name is required")
	@Size(min = 1, max = 100, message = "Company name must be between 1 and 100 characters")
	@Pattern(regexp = "^[a-zA-Z\\s'-]*$", message = "Company name must consist of letters, spaces, apostrophes, and dashes only")
	private String companyName;

	@Pattern(regexp = "[0-9]{10}", message = "Mobile number must be exactly 10 digits")
	private Long mobileNumber;

}