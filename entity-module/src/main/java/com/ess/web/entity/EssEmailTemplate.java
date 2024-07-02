package com.ess.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
public class EssEmailTemplate {

	@Id
	private long id;

	@Column(unique = true)
	private String templateId;

	private String emailSubject;

	private String emailText;

}
