package com.ess.web.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ess.web.entity.EssEmailTemplate;

@Repository
public interface EssEmailTemplateRepository extends JpaRepository<EssEmailTemplate, Long> {

	EssEmailTemplate findByTemplateId(String templateId);
}
