package personal.cheng.cryptobrick.cipher.decrypt;

import static org.junit.Assert.*;

import org.junit.Test;

import personal.cheng.cryptobrick.TestUtils;
import personal.cheng.cryptobrick.cipher.monoalphabetic.decrypt.DecryptAffineCipher;
import personal.cheng.cryptobrick.cipher.monoalphabetic.decrypt.DecryptCaesarCipher;
import personal.cheng.cryptobrick.cipher.monoalphabetic.decrypt.DecryptKeywordCipher;

/**
 * Tests the decryption ciphers.
 * 
 * <p>
 * Each test reads from their own input ciphertext and outputs to their own ciphertext
 * text file. This result is compared with a pre-loaded expected ciphertext.
 * </p>
 * 
 * @author chenga1
 */
public class DecryptTests 
{
	private final String PATH_TO_EXPECTED_FILE = "test/cipher/decrypt/expected.txt";
	
	@Test
	public void testCaesarCipher()
	{
		final String PATH_TO_INPUT_FILE = "test/cipher/decrypt/Caesar_input.txt";
		final String PATH_TO_OUTPUT_FILE = "test/cipher/decrypt/Caesar_actual.txt";
		
		DecryptCaesarCipher.run(PATH_TO_INPUT_FILE, PATH_TO_OUTPUT_FILE, 21);
		
		assertTrue( TestUtils.sameFile(PATH_TO_OUTPUT_FILE, PATH_TO_EXPECTED_FILE) );
	}
	
	@Test
	public void testAffineCipher()
	{
		final String PATH_TO_INPUT_FILE = "test/cipher/decrypt/Affine_input.txt";
		final String PATH_TO_OUTPUT_FILE = "test/cipher/decrypt/Affine_actual.txt";
		
		DecryptAffineCipher.run(PATH_TO_INPUT_FILE, PATH_TO_OUTPUT_FILE, 19, 3);
		
		assertTrue( TestUtils.sameFile(PATH_TO_OUTPUT_FILE, PATH_TO_EXPECTED_FILE) );
	}
	
	@Test
	public void testKeywordCipher()
	{
		final String PATH_TO_INPUT_FILE = "test/cipher/decrypt/Keyword_input.txt";
		final String PATH_TO_OUTPUT_FILE = "test/cipher/decrypt/Keyword_actual.txt";
		
		DecryptKeywordCipher.run(PATH_TO_INPUT_FILE, PATH_TO_OUTPUT_FILE, "FOOBARBAZ", "W");
		
		assertTrue( TestUtils.sameFile(PATH_TO_OUTPUT_FILE, PATH_TO_EXPECTED_FILE) );
	}

}
