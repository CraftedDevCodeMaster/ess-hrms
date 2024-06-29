package com.ess.web.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class EssUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Pattern(regexp = "^[a-zA-Z0-9_-]{1,20}$")
    private String empId;

    @NotBlank
    @Email
    private String email;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-zA-Z-' ]{1,50}$")
    private String firstName;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-zA-Z-' ]{1,50}$")
    private String lastName;

    private Date lastLoginDate;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$")
    private String password;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_.-]{3,20}$")
    private String loginName;

}
