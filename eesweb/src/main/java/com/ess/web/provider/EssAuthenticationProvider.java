package com.ess.web.provider;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ess.web.entity.EssUser;
import com.ess.web.entity.service.EssUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author PRAVEENREDDY R
 */
@Slf4j
@Component
public class EssAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private EssUserService essUserService;

	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();

		try {
			validateInput(email, password);

			EssUser essUser = essUserService.findByEmail(email);
			if (essUser == null) {
				log.error("User not found for email: {}", email);
				throw new BadCredentialsException("Authentication failed. User not found.");
			}

			if (!passwordEncoder.matches(password, essUser.getPassword())) {
				log.error("Invalid password for email: {}", email);
				throw new BadCredentialsException("Authentication failed. Invalid password.");
			}

			log.info("User {} authenticated successfully", email);
			return new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());

		} catch (IllegalArgumentException | BadCredentialsException e) {
			log.error("Authentication failed: {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error during authentication", e);
			throw new RuntimeException("Unexpected error occurred");
		}
	}

	private void validateInput(String email, String password) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		}

		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
