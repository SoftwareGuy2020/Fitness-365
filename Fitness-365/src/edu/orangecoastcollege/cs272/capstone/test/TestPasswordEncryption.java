package edu.orangecoastcollege.cs272.capstone.test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.orangecoastcollege.cs272.capstone.model.PasswordEncryption;

public class TestPasswordEncryption {

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	/**
	 * @throws Exception
	 */
	@Test
	public void testGenerateSalt() {
		byte[] b;
		try {
			b = PasswordEncryption.generateSalt();
			assertTrue(b.length > 0);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * @throws Exception
	 */
	@Test
	public void testGetEncryptedPassword() {
		try {
			byte[] b = PasswordEncryption.getEncryptedPassword("password", PasswordEncryption.generateSalt());
			assertTrue(b.length > 0);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	/**
	 * @throws Exception
	 */
	@Test
	public void testAuthenticate() {		
		try {
			byte[] salt = PasswordEncryption.generateSalt();
			assertTrue(PasswordEncryption.authenticate("password", 
					PasswordEncryption.getEncryptedPassword("password", salt), salt));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			fail("failed to authenticate");
		}
	}
}
