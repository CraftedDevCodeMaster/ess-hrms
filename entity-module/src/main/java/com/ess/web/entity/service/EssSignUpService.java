package com.ess.web.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ess.web.entity.EssSignUp;
import com.ess.web.entity.repository.EssSignUpRepository;

@Service
public class EssSignUpService {

	@Autowired
	private EssSignUpRepository essSignUpRepository;

	public void save(EssSignUp essSignUp) {
		essSignUpRepository.save(essSignUp);
	}

	public boolean emailExists(String email) {
		return essSignUpRepository.existsByEmail(email);
	}

	public EssSignUp findByEmail(String email) {
		return essSignUpRepository.findByEmail(email);
	}

}
