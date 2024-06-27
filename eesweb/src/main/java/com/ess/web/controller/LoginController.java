package com.ess.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/essDashboard")
	public String essDashboard() {
		return "essDashboard";
	}

}
