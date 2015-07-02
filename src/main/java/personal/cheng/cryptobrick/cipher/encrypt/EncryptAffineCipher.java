package personal.cheng.cryptobrick.cipher.encrypt;

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
 * For example, if a = 3 and b = 5, then A --> H, B --> K, C --> N, ...
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
			
		boolean removeSpaces = false;
		
		if (args.length > 4 && Boolean.valueOf(args[4]));
			removeSpaces = true;
		
		run(args[0], args[1], Integer.valueOf(args[2]), Integer.valueOf(args[3]), removeSpaces);
	}
	
	public static void run(String pathToInputFile, String pathToOutputFile, int a, int b, boolean removeSpaces)
	{
		try 
		{
			// Numbers not coprime with 26 are either even or divisible by 13
			if (Integer.valueOf(a) % 2 == 0 || Integer.valueOf(a) % 13 == 0)
				log.warn("WARNING: The value of 'a' in the affine cipher is not coprime with 26. "
						+ "The resulting message may be impossible to decrypt!");
			
			b %= 26;

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToOutputFile)));
			
			List<String> allLines = CryptobrickIO.readFile(pathToInputFile);
			
			for (int i = 0; i < allLines.size() - 1; i++)
			{
				writer.write(processLine(allLines.get(i), a, b, removeSpaces));
				writer.newLine();
			}
			writer.write(processLine(allLines.get(allLines.size()-1), a, b, removeSpaces));
			
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
				newChar -= 'A'-1;
				
				newChar *= a;
				newChar += b;
				
				newChar %= 26;
				if (newChar == 0) newChar = 26;
				
				newChar += 'A'-1;
			}
			
			out += newChar;
		}
		
		return out;
	}

}
