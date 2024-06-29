package com.ess.web;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

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
		http.cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(Collections.singletonList("*"));
			config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
			return config;
		})).csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.authenticationProvider(essAuthenticationProvider) // Inject your custom authentication provider
				.authorizeRequests(authorize -> authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
						.requestMatchers("/login", "/signUp", "/forgotPasswordForm").permitAll().anyRequest()
						.authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/essDashboard")
						.failureUrl("/login?error=true"))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
						.deleteCookies("JSESSIONID"));
		return http.build();
	}
//		// Configure CORS and CSRF
//		http.cors(withDefaults()).csrf(withDefaults())
//
//				.authenticationProvider(essAuthenticationProvider)
//
//				.authorizeRequests(authorize -> authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
//						.requestMatchers("/login","/signUp").permitAll().anyRequest().authenticated())
//
//				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/essDashboard")
//						.failureUrl("/login?error=true"))
//
//				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
//						.deleteCookies("JSESSIONID"));
//
//		return http.build();
//	}
}
