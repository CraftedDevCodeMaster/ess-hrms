package com.ess.web.controller;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ess.web.entity.EssSignUp;
import com.ess.web.entity.EssUser;
import com.ess.web.entity.OTPDetails;
import com.ess.web.entity.OTPManager;
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
	private final Random random = new SecureRandom();

	@Autowired
	private EssSignUpService essSignUpService;

	@Autowired
	private EssUserService essUserService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/signUp")
	public String essSign(Model model, CsrfToken token) {
		model.addAttribute("essSignUp", new EssSignUp());
		model.addAttribute("_csrf", token);
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

	// OTP

	@PostMapping("/send-otp")
	public ResponseEntity<String> sendOTP(@RequestParam("email") String email) {
		// Generate OTP
		String otp = generateOtp();

		// Store OTP with email and timestamp
		OTPManager.storeOTP(email, otp, LocalDateTime.now());

		// Simulate sending OTP via email

		sendOTPEmail(email, otp);

		// Return a JSON response indicating success
		return ResponseEntity.ok("OTP sent successfully to " + email);
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestParam("email") String email, @RequestParam("otp") String otp) {
		OTPDetails otpDetails = OTPManager.getOTPDetails(email);

		if (otpDetails != null && otp.equals(otpDetails.getOtp())) {
			OTPManager.removeOTP(email);
			return ResponseEntity.ok("Email verified successfully");
		} else {
			// Invalid OTP or email not found
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP verification failed");
		}
	}

	public String generateOtp() {
		int otpLength = Integer.parseInt(env.getProperty("otp.length"));
		StringBuilder otp = new StringBuilder(otpLength);
		for (int i = 0; i < otpLength; i++) {
			otp.append(random.nextInt(10));
		}
		return otp.toString();
	}

	public void sendOTPEmail(String email, String otp) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			// Set recipient and subject
			helper.setTo(email);
			helper.setSubject("OTP Verification");

			// HTML content with embedded image and OTP
			String htmlContent = "<html><body>" + "<h3>Welcome to Reddy's Portal!</h3>"
					+ "<p>Thank you for creating an account with us.</p>" + "<p>Your OTP for verification is: <strong>"
					+ otp + "</strong></p>" + "</body></html>";

			helper.setText(htmlContent, true);
			// Send email
			javaMailSender.send(message);

		} catch (Exception e) {
			System.out.println("Error sending OTP email  " + e.getMessage());
			// Handle exception or log error
		}
	}

	@GetMapping("/essDashboard")
	public String essDashboard() {
		return "essDashboard";
	}
}
