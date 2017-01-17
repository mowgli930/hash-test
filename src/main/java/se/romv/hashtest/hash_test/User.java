package se.romv.hashtest.hash_test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class User {
	
	String username;
	byte[] salt;
	byte[] hash;
	
	public User(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.username = username;
		salt = Hash.generateSalt(password);
		hash = Hash.generateHash(password.toCharArray(), salt);
	}

	public String getUsername() {
		return this.username;
	}

	public byte[] getSalt() {
		return salt;
	}

	public byte[] getHash() {
		return hash;
	}
}
