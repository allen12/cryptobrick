package personal.cheng.cryptobrick.cipher.encrypt;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import personal.cheng.cryptobrick.TestUtils;
import personal.cheng.cryptobrick.cipher.monoalphabetic.encrypt.EncryptAffineCipher;
import personal.cheng.cryptobrick.cipher.monoalphabetic.encrypt.EncryptCaesarCipher;
import personal.cheng.cryptobrick.cipher.monoalphabetic.encrypt.EncryptKeywordCipher;
import personal.cheng.cryptobrick.cipher.polyalphabetic.encrypt.EncryptVigenereCipher;

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
	
	@Test
	public void testKeywordCipher()
	{
		final String PATH_TO_OUTPUT_FILE = "test/cipher/encrypt/Keyword_actual.txt";
		final String PATH_TO_EXPECTED_FILE = "test/cipher/encrypt/Keyword_expected.txt";
		
		EncryptKeywordCipher.run(PATH_TO_INPUT_FILE, PATH_TO_OUTPUT_FILE, "FOOBARBAZ", "W", true);
		
		assertTrue( TestUtils.sameFile(PATH_TO_OUTPUT_FILE, PATH_TO_EXPECTED_FILE) );
	}
	
	@Test
	public void testVigenereCipher()
	{
		final String PATH_TO_OUTPUT_FILE = "test/cipher/encrypt/Vigenere_actual.txt";
		final String PATH_TO_EXPECTED_FILE = "test/cipher/encrypt/Vigenere_expected.txt";
		
		EncryptVigenereCipher.run(PATH_TO_INPUT_FILE, PATH_TO_OUTPUT_FILE, "WACK", false);
		
		assertTrue( TestUtils.sameFile(PATH_TO_OUTPUT_FILE, PATH_TO_EXPECTED_FILE) );
	}

}
