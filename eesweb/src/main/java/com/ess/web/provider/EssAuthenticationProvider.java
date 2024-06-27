package com.ess.web.provider;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EssAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		// Replace with your actual authentication logic
		if ("pravin".equals(name) && "reddy".equals(password)) {
			log.info("LOGIN SUCCESSFUL: {}", name);
			return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
		} else {
			log.error("LOGIN FAILED: {}", name);
			throw new RuntimeException("Authentication failed");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
