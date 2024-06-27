package com.ess.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/loginform")
	public String loginForm1() {
		return "essDashboard";
	}

	@GetMapping("/essDashboard")
	public String essDashboard() {
		return "essDashboard";
	}

}
