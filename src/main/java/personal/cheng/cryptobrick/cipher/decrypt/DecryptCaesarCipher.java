package personal.cheng.cryptobrick.cipher.decrypt;

import org.apache.log4j.Logger;

import personal.cheng.cryptobrick.cipher.encrypt.EncryptCaesarCipher;

/**
 * <p>
 * A Caesar cipher is the simplest and most widely known encryption technique.
 * </p>
 * 
 * <p>
 * Each letter in the plaintext is replaced by a letter a fixed number of
 * positions down the alphabet, known as the key.
 * </p>
 * 
 * <p>
 * For example, with a key of 3, each letter is shifted down three character positions.
 * A --> D, B --> E, ... , X --> A, Y --> B, Z --> C
 * </p>
 * 
 * <p>
 * As a result, to decrypt a message encoded in this manner, it is sufficient to
 * repeat the encryption process with the additive modulus inverse of the key.
 * 
 * @author Cheng, Allen
 */
public class DecryptCaesarCipher 
{
	private static final Logger log = Logger.getLogger(DecryptCaesarCipher.class);
	
	public static void main(String[] args) 
	{
		if (args.length < 3)
		{
			log.fatal("Usage: java DecryptCaesarCipher <input.txt> <output.txt> <key>");
			return;
		}

		int key = Integer.valueOf(args[2]);
		key %= 26;
		key = 26 - key;
		
		EncryptCaesarCipher.run(args[0], args[1], key, false);
	}
}
