package com.websphere.api.encryption;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class BCrypt {
	
	public static class Encryption {
		
		public static String encryptPassword(String password) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);
			String encryptedPass = encoder.encode(password);
			return encryptedPass;
		}
		
		public static boolean verifyPassword(String rawPassword, String hashedPassword) {
			boolean result = false;
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);
			result = encoder.matches(rawPassword, hashedPassword);
			return result;
		}
	}
	
}



