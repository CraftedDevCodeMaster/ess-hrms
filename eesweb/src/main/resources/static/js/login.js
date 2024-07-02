		// Function to toggle password visibility
		function togglePasswordVisibility() {
			var passwordInput = document.getElementById('password');
			var icon = document.getElementById('eye-icon');

			if (passwordInput.type === 'password') {
				passwordInput.type = 'text';
				icon.classList.remove('fa-eye-slash');
				icon.classList.add('fa-eye');
			} else {
				passwordInput.type = 'password';
				icon.classList.remove('fa-eye');
				icon.classList.add('fa-eye-slash');
			}

			// Fix for placeholder disappearing issue
			passwordInput.focus(); // Focus on the input to trigger placeholder display
			passwordInput.blur(); // Blur immediately to reset focus
		}

		// Event listener for clicking on the eye icon
		document.getElementById('eye-icon').addEventListener('click', function () {
			togglePasswordVisibility();
		});

		// Function to validate email format and enable/disable password and login button
		function validateEmail() {
			var emailInput = document.getElementById('email');
			var emailError = document.getElementById('emailError');
			var loginButton = document.getElementById('loginButton');
			var passwordInput = document.getElementById('password');
			var email = emailInput.value;
			var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

			if (!emailRegex.test(email)) {
				emailError.textContent = 'Enter a valid email address';
				loginButton.setAttribute('disabled', 'disabled');
				passwordInput.setAttribute('disabled', 'disabled');
			} else {
				emailError.textContent = '';
				loginButton.removeAttribute('disabled');
				passwordInput.removeAttribute('disabled');
			}
		}