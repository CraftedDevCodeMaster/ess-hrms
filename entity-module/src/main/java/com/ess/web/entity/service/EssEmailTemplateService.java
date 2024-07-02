package com.ess.web.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ess.web.entity.EssEmailTemplate;
import com.ess.web.entity.repository.EssEmailTemplateRepository;

@Service
public class EssEmailTemplateService {

	@Autowired
	private EssEmailTemplateRepository emailTemplateRepository;

	public EssEmailTemplate findByTemplateId(String templateId) {
		return emailTemplateRepository.findByTemplateId(templateId);
	}

}
