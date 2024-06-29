package com.ess.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author PRAVEENREDDY R
 */
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

	@GetMapping("/signUp")
	public String essSign() {
		return "signUp";
	}
	
	 @GetMapping("/forgotPasswordForm")
	    public String showForgotPasswordForm() {
	        // Return Thymeleaf template for the forgot password form
	        return "forgotPasswordForm";
	    }

	    @PostMapping("/forgotPassword")
	    public String processForgotPasswordForm(@RequestParam("email") String email) {

	        return "redirect:/login?forgotPasswordSuccess";
	    }

	    // Example method for handling password reset confirmation and update
	    @PostMapping("/resetPassword")
	    public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
	        // Implement logic to verify token and update password securely
	        // Redirect to login page after password reset
	        return "redirect:/login?passwordResetSuccess";
	    }

}
