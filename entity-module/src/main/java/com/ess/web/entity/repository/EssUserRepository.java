package com.ess.web.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ess.web.entity.EssUser;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EssUserRepository extends JpaRepository<EssUser, Long> {

	boolean existsByEmailAndPassword(String email, String password);

	EssUser findByEmail(String email);
}
