package personal.cheng.cryptobrick.cipher.encrypt;

import static org.junit.Assert.*;

import org.junit.Test;

import personal.cheng.cryptobrick.TestUtils;

/**
 * Tests the encryption ciphers.
 * 
 * <p>
 * Each test reads from the same input plaintext, but outputs to their own ciphertext
 * text file. This result is compared with a pre-loaded expected ciphertext.
 * </p>
 * 
 * @author chenga1
 *
 */
public class EncryptTests 
{

	private final String PATH_TO_INPUT_FILE = "test/cipher/encrypt/input.txt";

	@Test
	public void testCaesarCipher() 
	{
		final String PATH_TO_OUTPUT_FILE = "test/cipher/encrypt/Caesar_actual.txt";
		final String PATH_TO_EXPECTED_FILE = "test/cipher/encrypt/Caesar_expected.txt";
		
		EncryptCaesarCipher.run(PATH_TO_INPUT_FILE, PATH_TO_OUTPUT_FILE, 5, true);
		
		assertTrue( TestUtils.sameFile(PATH_TO_OUTPUT_FILE, PATH_TO_EXPECTED_FILE) );
	}
	
	@Test
	public void testAffineCipher()
	{
		final String PATH_TO_OUTPUT_FILE = "test/cipher/encrypt/Affine_actual.txt";
		final String PATH_TO_EXPECTED_FILE = "test/cipher/encrypt/Affine_expected.txt";
		
		EncryptAffineCipher.run(PATH_TO_INPUT_FILE, PATH_TO_OUTPUT_FILE, 7, 15, true);
		
		assertTrue( TestUtils.sameFile(PATH_TO_OUTPUT_FILE, PATH_TO_EXPECTED_FILE) );
	}
	

}
