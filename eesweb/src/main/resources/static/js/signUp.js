// Function to toggle password visibility
function togglePasswordVisibility(inputId) {
	var passwordInput = document.getElementById(inputId);
	var icon = document.getElementById(inputId + 'Icon');

	if (passwordInput.type === 'password') {
		passwordInput.type = 'text';
		icon.classList.remove('fa-eye-slash');
		icon.classList.add('fa-eye');
	} else {
		passwordInput.type = 'password';
		icon.classList.remove('fa-eye');
		icon.classList.add('fa-eye-slash');
	}

	// Focus on the input to trigger placeholder display
	passwordInput.focus();
	// Blur immediately to reset focus
	passwordInput.blur();
}

// Function to validate password strength
function validatePassword() {
	var newPasswordInput = document.getElementById('newPassword');
	var confirmPasswordInput = document.getElementById('confirmPassword');
	var newPasswordError = document.getElementById('newPasswordError');
	var confirmPasswordError = document.getElementById('confirmPasswordError');
	var signupButton = document.getElementById('signupButton');

	// Reset error messages
	newPasswordError.textContent = '';
	confirmPasswordError.textContent = '';

	// Password validation
	if (newPasswordInput.value.length < 6) {
		newPasswordError.textContent = 'Password must be at least 6 characters long';
		signupButton.disabled = true; // Disable sign up button
		confirmPasswordInput.disabled = true; // Disable confirm password field
	} else {
		signupButton.disabled = false; // Enable sign up button
		confirmPasswordInput.disabled = false; // Enable confirm password field
	}

	if (newPasswordInput.value !== confirmPasswordInput.value) {
		confirmPasswordError.textContent = 'Passwords do not match';
		signupButton.disabled = true; // Disable sign up button
	}
}

// Initial call to validate password fields on page load
validatePassword();



// Function to validate email and show OTP input section
function validateEmailAndShowOTP() {
	var email = document.getElementById('workEmail').value;

	// Validate email format
	if (!isValidEmail(email)) {
		alert('Please enter a valid email address.');
		return;
	}

	// Call the API to send OTP
	sendOTP(email);
}


// Function to validate email format
function isValidEmail(email) {
	// Basic email format validation
	var regex = /\S+@\S+\.\S+/;
	return regex.test(email);
}


// Function to send OTP via backend API
async function sendOTP(email) {
	try {
		let response = await fetch('/ess/send-otp', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: new URLSearchParams({ email: email })
		});

		if (response.ok) {
			let data = await response.text();
			// Show success message
			showFormMessage('successMessage', data, true);
			// Show OTP input field after success message
			document.getElementById('sendOTPButton').style.display = 'none';
			document.getElementById('otpInputSection').style.display = 'block';
		} else {
			let errorMessage = await response.text();
			// Show failure message
			showFormMessage('failureMessage', `Failed to send OTP: ${errorMessage}`, false);
		}
	} catch (error) {
		console.error('Error sending OTP:', error);
		showFormMessage('failureMessage', 'An error occurred while sending the OTP.', false);
	}
}

// Function to verify OTP using backend API
async function verifyOTP() {
	var otp = document.getElementById('otp').value;
	var email = document.getElementById('workEmail').value;

	try {
		let response = await fetch('/ess/verify-otp?email=' + email + '&otp=' + otp, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			// body: JSON.stringify({ email: email, otp: otp }) // No need for this if using @RequestParam
		});

		if (response.ok) {
			let data = await response.text();
			// Show success message
			showFormMessage('successMessage', data, true);
			document.getElementById('otpInputSection').style.display = 'none';
		} else {
			let errorMessage = await response.text();
			// Show failure message
			showFormMessage('failureMessage', `Failed to verify OTP: ${errorMessage}`, false);
		}
	} catch (error) {
		console.error('Error verifying OTP:', error);
		showFormMessage('failureMessage', 'An error occurred while verifying the OTP', false);
	}
}



// Function to display messages on the form
function showFormMessage(id, message, isSuccess) {
	var messageElement = document.getElementById(id);

	// Hide all message elements initially
	document.getElementById('successMessage').style.display = 'none';
	document.getElementById('failureMessage').style.display = 'none';

	// Set the message content and class based on isSuccess flag
	messageElement.textContent = message;
	messageElement.className = isSuccess ? 'alert alert-success' : 'alert alert-danger';

	// Display the message element
	messageElement.style.display = 'block';
}

