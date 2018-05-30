package edu.orangecoastcollege.cs272.capstone.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Wraps up the various password encryption and authentication methods used in the program.
 * @author Travis
 *
 */
public final class PasswordEncryption {
	
	/**
	 * Authenticates the plaintext password with the hashes to verify if the log in should be authorized.
	 * @param password the typed password
	 * @param hash the stored password hash of the user
	 * @param salt the salt used in originally hashing the password
	 * @return True if authentication is successful. False otherwise.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static final boolean authenticate(String password, byte[] hash, byte[] salt)
						throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		byte[] encryptedPassword = getEncryptedPassword(password, salt);
		return Arrays.equals(encryptedPassword, hash);
	}

	/**
	 * Gets the encrypted hash for the plaintext password
	 * @param password the plaintext password
	 * @param salt 
	 * @return the encryped password hash
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static final byte[] getEncryptedPassword(String password, byte[] salt)
					throws NoSuchAlgorithmException, InvalidKeySpecException {
		String algorithm = "PBKDF2WithHmacSHA1";
		int keyLen = 160; // key is 160bits		
		KeySpec ks = new PBEKeySpec(password.toCharArray(), salt, 20_000, keyLen);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
		
		return factory.generateSecret(ks).getEncoded(); 
	}
	
	/**
	 * Generates a random salt.
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		return salt;
	}
}
