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
 * An affine cipher is a type of monoalphabetic substitution cipher, encrypted
 * with a mathematical function (ax + b) mod 26, where <i>b</i> is the magnitude of the
 * shift. Therefore, if <i>a</i> = 1, then the cipher reduces to a Caesar shift.
 * </p>
 * 
 * <p>
 * The magnitude of <i>a</i> and the number 26 must be coprime.
 * If this is not the case, then the resulting message may be impossible to decrypt
 * because then there exists no multiplicative inverse of <i>a</i>.
 * </p>
 *
 * <p>
 * For example, if a = 3 and b = 5
 * </p>
 * 
 * @author Cheng, Allen
 */
public class EncryptAffineCipher 
{
	private static final Logger log = Logger.getLogger(EncryptAffineCipher.class);
	
	public static void main(String[] args) 
	{
		if (args.length < 4)
		{
			log.fatal("Usage: java EncryptAffineCipher <input.txt> <output.txt> <a> <b> <opt: remove spaces>");
			return;
		}
		
		// Numbers not coprime with 26 are either even or divisible by 13
		if (Integer.valueOf(args[2]) % 2 == 0 || Integer.valueOf(args[2]) % 13 == 0)
			log.warn("WARNING: The value of 'a' in the affine cipher is not coprime with 26. "
					+ "The resulting message may be impossible to decrypt!");
		
		boolean removeSpaces = false;
		
		if (args.length > 4 && Boolean.valueOf(args[4]));
			removeSpaces = true;
		
		run(args[0], args[1], Integer.valueOf(args[2]), Integer.valueOf(args[3]), removeSpaces);
	}
	
	public static void run(String pathToInputFile, String pathToOutputFile, int a, int b, boolean removeSpaces)
	{
		try 
		{
			b %= 26;
			
			BufferedReader reader = new BufferedReader(new FileReader(new File(pathToInputFile)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToOutputFile)));
			
			while (reader.ready())
			{
				String line = reader.readLine();
				writer.write(processLine(line, a, b, removeSpaces));
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
	
	private static String processLine(String in, int a, int b, boolean removeSpaces)
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
				newChar *= a;
				newChar += b;
				
				while (newChar > 'Z')
					newChar -= 26;
			}
			
			out += newChar;
		}
		
		return out;
	}

}
