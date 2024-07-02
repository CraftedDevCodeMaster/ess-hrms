package com.ess.web.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OTPManager {
    private static final Map<String, OTPDetails> otpStorage = new HashMap<>();

    // Method to store OTP and associated details
    public static void storeOTP(String email, String otp, LocalDateTime timestamp) {
        otpStorage.put(email, new OTPDetails(otp, timestamp));
    }

    // Method to retrieve OTP details by email
    public static OTPDetails getOTPDetails(String email) {
        return otpStorage.get(email);
    }

    // Method to remove OTP details after successful verification or expiry
    public static void removeOTP(String email) {
        otpStorage.remove(email);
    }
}

