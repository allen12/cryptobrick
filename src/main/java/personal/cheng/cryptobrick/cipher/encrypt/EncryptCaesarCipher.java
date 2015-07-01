package personal.cheng.cryptobrick.cipher.encrypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import personal.cheng.cryptobrick.util.CryptobrickException;

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
 * @author Cheng, Allen
 */
public class EncryptCaesarCipher 
{
	private static final Logger log = Logger.getLogger(EncryptCaesarCipher.class);
	
	public static void main(String[] args) 
	{
		if (args.length < 3)
		{
			log.fatal("Usage: java EncryptCaesarCipher <input.txt> <output.txt> <key> <opt: remove spaces>");
			return;
		}
		
		boolean removeSpaces = false;
		
		if (args.length > 3 && Boolean.valueOf(args[3]));
			removeSpaces = true;
		
		run(args[0], args[1], Integer.valueOf(args[2]), removeSpaces);
	}
	
	public static void run(String pathToInputFile, String pathToOutputFile, int key, boolean removeSpaces)
	{
		try 
		{
			key %= 26;
			
			BufferedReader reader = new BufferedReader(new FileReader(new File(pathToInputFile)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToOutputFile)));
			
			while (reader.ready())
			{
				String line = reader.readLine();
				writer.write(processLine(line, key, removeSpaces));
				writer.newLine();
			}
			
			reader.close();
			writer.flush();
			writer.close();
			
		} catch (IOException e) 
		{
			throw new CryptobrickException(e);
		}
	}
	
	private static String processLine(String in, int key, boolean removeSpaces)
	{
		String out = "";
		
		in = in.toUpperCase();
		
		char[] characters = in.toCharArray();
		for (char c : characters)
		{
			// If removeSpaces option is true and character is a space, skip
			if (removeSpaces == true && c == ' ')
				continue;
			
			char newChar = c;
			
			if (newChar >= 'A' && newChar <= 'Z')
			{
				newChar += key;
				
				if (newChar > 'Z')
					newChar -= 26;
			}
			
			out += newChar;
		}
		
		return out;
	}

}
