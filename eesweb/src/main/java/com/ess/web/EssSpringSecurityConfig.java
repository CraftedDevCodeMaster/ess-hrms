package com.ess.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.Assert;

@Configuration
@EnableWebSecurity
@ConditionalOnExpression("'${Ess_security}'.equals('Ess')")
public class EssSpringSecurityConfig {

	@Autowired
	private AuthenticationProvider essAuthenticationProvider;

	@Autowired
	public EssSpringSecurityConfig(AuthenticationProvider essAuthenticationProvider) {
		Assert.notNull(essAuthenticationProvider, "AUTH not be null");
		this.essAuthenticationProvider = essAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(essAuthenticationProvider)
				.authorizeRequests(authorize -> authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
						.requestMatchers("/login").permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/essDashboard"));
		return http.build();
	}
}
