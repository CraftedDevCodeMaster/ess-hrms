package com.ess.web;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class EssSpringSecurityConfig {

	@Autowired
	private AuthenticationProvider essAuthenticationProvider;

	public EssSpringSecurityConfig(AuthenticationProvider essAuthenticationProvider) {
		this.essAuthenticationProvider = essAuthenticationProvider;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.cors(withDefaults()).csrf(withDefaults()).authenticationProvider(essAuthenticationProvider)
                .authorizeRequests(authorize -> authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/login").permitAll().anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/essDashboard"));
		return http.build();
	}
}
