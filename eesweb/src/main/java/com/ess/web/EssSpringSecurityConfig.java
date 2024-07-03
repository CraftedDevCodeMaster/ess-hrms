package com.ess.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class EssSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthenticationProvider essAuthenticationProvider;

	public EssSpringSecurityConfig(AuthenticationProvider essAuthenticationProvider) {
		this.essAuthenticationProvider = essAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll()
				.antMatchers("/", "/login", "/signUp", "/forgotPasswordForm", "/verify-otp", "/send-otp",
						"/send-otp-reset")
				.permitAll().anyRequest().authenticated().and().authenticationProvider(essAuthenticationProvider)
				.formLogin().loginPage("/login").defaultSuccessUrl("/essDashboard").failureUrl("/login?error=true")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
	}
}
