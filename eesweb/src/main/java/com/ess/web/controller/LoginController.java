package com.ess.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ess.web.entity.EssSignUp;
import com.ess.web.entity.EssUser;
import com.ess.web.entity.service.EssSignUpService;
import com.ess.web.entity.service.EssUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author PRAVEENREDDY R
 */
@Slf4j
@Controller
public class LoginController {

	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private EssSignUpService essSignUpService;

	@Autowired
	private EssUserService essUserService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/signUp")
	public String essSign(Model model) {
		model.addAttribute("essSignUp", new EssSignUp());
		return "signUp";
	}

	@PostMapping("/signUp")
	public String signup(@ModelAttribute("essSignUp") EssSignUp essSignUp, RedirectAttributes redirectAttributes) {
		if (essSignUp == null) {
			redirectAttributes.addFlashAttribute("message", "All fields are required");
			return "redirect:/signUp";
		}

		if (essSignUpService.emailExists(essSignUp.getEmail())) {
			redirectAttributes.addFlashAttribute("message", "Account already exists. Please login.");
			return "redirect:/signUp";
		}

		if (essSignUp.getNewPassword().equals(essSignUp.getConfirmPassword())) {
			String plainPassword = essSignUp.getNewPassword();
			essSignUp.setNewPassword(passwordEncoder.encode(plainPassword));
			essSignUp.setCreateDate(new Date());
			essSignUp.setLastModifiedDate(new Date());

			essSignUpService.save(essSignUp);

			EssUser essUser = new EssUser();
			essUser.setEmail(essSignUp.getEmail());
			essUser.setLastLoginDate(new Date());
			essUser.setPassword(essSignUp.getNewPassword());
			essUser.setUserName(String.format("%s %s", essSignUp.getFirstName(), essSignUp.getLastName()));

			essUserService.save(essUser);

			redirectAttributes.addFlashAttribute("successMessage", true);
			return "redirect:/signUp";
		} else {
			redirectAttributes.addFlashAttribute("message", "New password and confirm password do not match");
			return "redirect:/signUp";
		}
	}

	@GetMapping("/forgotPasswordForm")
	public String showForgotPasswordForm() {
		return "forgotPasswordForm";
	}

	@PostMapping("/forgotPasswordForm")
	public String processForgotPasswordForm(@RequestParam("email") String email,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassowrd,
			RedirectAttributes redirectAttributes) {

		log.info("emial:: {}, newpaw:: {}, conpas:: {}", email, newPassword, confirmPassowrd);
		if (!essUserService.existsByEmail(email) && !essSignUpService.emailExists(email) || email == null) {
			redirectAttributes.addFlashAttribute("message", "Please enter a valid email address");
			return "redirect:/forgotPasswordForm";
		}
		if (!newPassword.isEmpty() && !confirmPassowrd.isEmpty() && newPassword.equals(confirmPassowrd)
				&& essUserService.existsByEmail(email) && essSignUpService.emailExists(email)) {
			EssUser essUser = essUserService.findByEmail(email);
			essUser.setPassword(passwordEncoder.encode(newPassword));
			essUserService.save(essUser);

			EssSignUp essSignUp = essSignUpService.findByEmail(email);
			essSignUp.setNewPassword(essUser.getPassword());
			essSignUp.setLastModifiedDate(new Date());

			essSignUpService.save(essSignUp);

			redirectAttributes.addFlashAttribute("successMessage", true);

			redirectAttributes.addFlashAttribute("successMessage", "Password changed successfully. Please login");
			return "redirect:/forgotPasswordForm";
		}

		else {
			redirectAttributes.addFlashAttribute("message", "Please enter the requried inputs");

			return "redirect:/forgotPasswordForm";
		}
	}

	@GetMapping("/essDashboard")
	public String essDashboard() {
		return "essDashboard";
	}
}
