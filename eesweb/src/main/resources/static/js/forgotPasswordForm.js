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

		// Function to validate password strength and enable/disable confirm password field
		function validatePassword() {
			var newPasswordInput = document.getElementById('newPassword');
			var confirmPasswordInput = document.getElementById('confirmPassword');
			var newPasswordError = document.getElementById('newPasswordError');
			var confirmPasswordError = document.getElementById('confirmPasswordError');
			var submitButton = document.querySelector('button[type="submit"]');

			// Reset error messages
			newPasswordError.textContent = '';
			confirmPasswordError.textContent = '';

			// Password validation
			if (newPasswordInput.value.length < 6) {
				newPasswordError.textContent = 'Password must be at least 6 characters long';
				submitButton.disabled = true; // Disable submit button
				confirmPasswordInput.disabled = true; // Disable confirm password field
			} else {
				submitButton.disabled = false; // Enable submit button
				confirmPasswordInput.disabled = false; // Enable confirm password field
			}

			if (newPasswordInput.value !== confirmPasswordInput.value) {
				confirmPasswordError.textContent = 'Passwords do not match.';
				submitButton.disabled = true; // Disable submit button
			}
		}