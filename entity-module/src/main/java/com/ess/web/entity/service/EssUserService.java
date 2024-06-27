package com.ess.web.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ess.web.entity.repository.EssUserRepository;

@Service
public class EssUserService {

	@Autowired
	private EssUserRepository essUserRepository;

}
