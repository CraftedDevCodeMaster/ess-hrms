package com.ess.web.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ess.web.entity.EssSignUp;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EssSignUpRepository extends JpaRepository<EssSignUp, Long> {

	boolean existsByEmail(String email);

	EssSignUp findByEmail(String email);
}
