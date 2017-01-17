package se.romv.hashtest.hash_test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class Hash {
	
	private static HashMap<String, User> users = new HashMap<String, User>();
	
	public static void save(User user) {
		users.put(user.getUsername(), user);
	}
	public static byte[] generateSalt(String password) {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[256 - password.length()];
		random.nextBytes(bytes);
		return Base64.getEncoder().encode(bytes);
	}
	
	public static byte[] generateHash(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password, salt, 5, 256);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		return factory.generateSecret(spec).getEncoded();
	}
	
	public static boolean validate(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User saved = users.get(username);
		byte[] savedSalt = saved.getSalt();
		byte[] hash = generateHash(password.toCharArray(), savedSalt);
		
		if(Arrays.equals(hash, saved.getHash()))
			return true;
		else
			return false;
	}

}
