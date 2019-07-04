package com.banking.api.encryption;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {
	
	public static class Encryption {
		
		public static String encryptPass(String password) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);
			String hashedPassword = encoder.encode(password);
			return hashedPassword;
		}
		
		public static boolean verifyPass(String rawPass, String pass) {
			boolean result = false;
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);
			result = encoder.matches(rawPass, pass);
			return result;
		}
		
	}
}