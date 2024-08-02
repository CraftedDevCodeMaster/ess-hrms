# ESS-HRMS

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.6.3-blue)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)

## Overview

ESS-HRMS (Employee Self-Service Human Resource Management System) is a Spring Boot-based application designed to manage HR-related activities. This project is organized as a multi-module Maven project.

## Features

- **Spring Boot 2.5.0**: Backend framework for developing the REST API.
- **Java 17**: Programming language used for backend development.
- **Maven**: Tool for managing dependencies and building the project.
- **RESTful APIs**: For managing HR data.
- **CRUD Operations**: Create, Read, Update, and Delete employee records.

## Prerequisites

- Java 17
- Maven 3.6.3 or higher

## Getting Started

### Clone the Repository

```
git clone https://github.com/CraftedDevCodeMaster/ess-hrms.git
cd ess-hrms
```

### Build the Project

```
mcn clean install 
cd ess-hrms
```
## Running the Application
- To run the application, navigate to the eesweb module and use the following command:
```
cd eesweb
mvn spring-boot:run
```

# Project Screenshots

## Home Page
- **Login**  
  Click this button to access your existing account. If you are already a user, this will direct you to the login page where you can enter your credentials to access your dashboard and HR tools.

- **Get Started**  
  Click this button to begin the sign-up process if you are a new user. This will take you to the sign-up page where you can create a new account and start using our HR management features.
  
 ![image](https://github.com/user-attachments/assets/a3507afd-83db-451e-8992-56f63b116ca6)

## Sign-Up Page

The sign-up page allows new users to create an account with the following fields:

1. **First Name***  
   *Required*: Enter your first name for personalization.

2. **Last Name**  
   *Optional*: Enter your last name for full identification.

3. **Work Email***  
   *Required*: Provide your email for account notifications.

4. **Phone Number***  
   *Required*: Enter your phone number for contact and verification.

5. **Company Name***  
   *Required*: Specify your company for account association.

6. **Enter Password***  
   *Required*: Create a secure password meeting specified criteria.

7. **Confirm Password***  
   *Required*: Re-enter the password to confirm it matches.

8. **Already have an account? Login**  
   *Link*: Navigate to the login page if you already have an account.

   
![image](https://github.com/user-attachments/assets/5ab24abf-a757-4e84-bf35-8540dc8faad7)


## Login Page

The login page allows users to access their accounts with the following elements:

1. **Logo**  
   *Description*: The logo is prominently displayed at the top of the page, representing the project or company.

2. **Email**  
   *Input Field*:  
   - *Placeholder*: `admin@example.com`  
   - *Description*: Enter your registered email address. This is used to identify your account.

3. **Password**  
   *Input Field*:  
   - *Placeholder*: `•••••••••`  
   - *Description*: Enter your password. Ensure it meets any specified security criteria.

4. **Forgot Password?**  
   *Link*:  
   - *Description*: Click this link if you have forgotten your password. It will guide you through the process to reset your password.

5. **Enter Captcha**  
   *Input Field*:  
   - *Description*: Complete the CAPTCHA to verify that you are not a bot. This helps to secure the login process.

6. **Don't have an account? Sign Up**  
   *Link*:  
   - *Description*: Click this link to navigate to the sign-up page if you do not have an account yet. This will allow you to create a new account.
  
   
  ![image](https://github.com/user-attachments/assets/f84b3606-5c5c-4278-81fd-b1c8cfe453e2)


  ## Change Password Page

The change password page allows users to update their password with the following fields and options:

1. **Logo**  
   *Description*: The logo is displayed at the top of the page, representing the project or company.

2. **Email**  
   *Input Field*:  
   - *Placeholder*: `Enter your email`  
   - *Description*: Enter your registered email address. This is used to send a One-Time Password (OTP) for verification.

3. **Send OTP**  
   *Button*:  
   - *Description*: Click this button to receive an OTP sent to your registered email address. The OTP is used to verify your identity before changing your password.

4. **Enter OTP**  
   *Input Field*:  
   - *Placeholder*: `Enter OTP`  
   - *Description*: Enter the OTP sent to your email to verify your identity.

5. **New Password**  
   *Input Field*:  
   - *Placeholder*: `Enter new password`  
   - *Description*: Create a new secure password for your account. Ensure it meets any specified security criteria.

6. **Confirm Password**  
   *Input Field*:  
   - *Placeholder*: `Confirm new password`  
   - *Description*: Re-enter the new password to confirm it matches the first password field.

7. **Already have an account? Login**  
   *Link*:  
   - *Description*: Click this link to navigate to the login page if you already have an account.

8. **Don't have an account? Sign Up**  
   *Link*:  
   - *Description*: Click this link to navigate to the sign-up page if you do not have an account yet. This will allow you to create a new account.
     
 ![image](https://github.com/user-attachments/assets/027b15bb-fca9-4521-930d-708c680b9917)

### OTP template 

![image](https://github.com/user-attachments/assets/b7597717-e06c-427f-9aef-65ee4d7c299b)







