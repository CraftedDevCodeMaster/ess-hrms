package com.ess.web.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ess.web.entity.EssUser;
import com.ess.web.entity.repository.EssUserRepository;

@Service
public class EssUserService {

	@Autowired
	private EssUserRepository essUserRepository;

	public void save(EssUser essUser) {
		essUserRepository.save(essUser);
	}

	public boolean existsByEmailAndPassword(String email, String password) {
		return essUserRepository.existsByEmailAndPassword(email, password);
	}

	public EssUser findByEmail(String email) {
		return essUserRepository.findByEmail(email);
	}

	public boolean existsByEmail(String email) {
		return essUserRepository.existsByEmail(email);
	}
}
