package personal.cheng.cryptobrick.cipher.monoalphabetic.decrypt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import personal.cheng.cryptobrick.util.CryptobrickException;
import personal.cheng.cryptobrick.util.CryptobrickIO;

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
		
		DecryptCaesarCipher.run(args[0], args[1], Integer.valueOf(args[2]));
	}
	
	public static void run(String pathToInputFile, String pathToOutputFile, int key)
	{
		try 
		{
			key %= 26;
			key = 26 - key;

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToOutputFile)));
			
			List<String> allLines = CryptobrickIO.readFile(pathToInputFile);
			
			for (int i = 0; i < allLines.size() - 1; i++)
			{
				writer.write(processLine(allLines.get(i), key));
				writer.newLine();
			}
			
			writer.write(processLine(allLines.get(allLines.size()-1), key));
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) 
		{
			throw new CryptobrickException(e);
		}
	}
	
	private static String processLine(String in, int key)
	{
		String out = "";
		
		in = in.toLowerCase();
		
		char[] characters = in.toCharArray();
		for (char c : characters)
		{
			char newChar = c;
			
			if (newChar >= 'a' && newChar <= 'z')
			{
				newChar += key;
				
				if (newChar > 'z')
					newChar -= 26;
			}
			
			out += newChar;
		}
		
		return out;
	}
}
