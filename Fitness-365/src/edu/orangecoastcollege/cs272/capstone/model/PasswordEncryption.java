package edu.orangecoastcollege.cs272.capstone.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @author Travis
 *
 */
public final class PasswordEncryption {
	
	public static final boolean authenticate(String password, byte[] hash, byte[] salt)
						throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		byte[] encryptedPassword = getEncryptedPassword(password, salt);
		return Arrays.equals(encryptedPassword, hash);
	}

	public static final byte[] getEncryptedPassword(String password, byte[] salt)
					throws NoSuchAlgorithmException, InvalidKeySpecException {
		String algorithm = "PBKDF2WithHmacSHA1";
		int keyLen = 160; // key is 160bits		
		KeySpec ks = new PBEKeySpec(password.toCharArray(), salt, 20_000, keyLen);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
		
		return factory.generateSecret(ks).getEncoded(); 
	}
	
	public static byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		return salt;
	}
}
