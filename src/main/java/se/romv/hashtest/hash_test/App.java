package se.romv.hashtest.hash_test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class App {
	public static void main(String[] args) {
		try {
			Hash.save(new User("Hello", "World"));
			System.out.println(Hash.validate("Hello", "World"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}
}
