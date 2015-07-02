package personal.cheng.cryptobrick.cipher.decrypt;

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
 * <p>
 * For decryption, it is necessary to first subtract <i>b</i> from the ciphertext position.
 * Then, multiply the result with the modular multiplicative inverse <i>x</i> of <i>a</i>, i.e.
 * <i>ax</i> = 1 (mod 26)
 * </p>
 * 
 * @author Cheng, Allen
 */
public class DecryptAffineCipher 
{
	
	private static final Logger log = Logger.getLogger(DecryptAffineCipher.class);

	public static void main(String[] args) 
	{
		if (args.length < 4)
		{
			log.fatal("Usage: java EncryptAffineCipher <input.txt> <output.txt> <a> <b>");
			return;
		}

		
		run(args[0], args[1], Integer.valueOf(args[2]), Integer.valueOf(args[3]));
	}
	
	public static void run(String pathToInputFile, String pathToOutputFile, int a, int b)
	{
		try 
		{
			// The modular multiplicative inverse only exists if coprime with the number 26
			if (Integer.valueOf(a) % 2 == 0 || Integer.valueOf(a) % 13 == 0)
				throw new CryptobrickException("The value of 'a' in the affine cipher is not coprime with 26!");
			
			b %= 26;
			int x = modularMultiplicativeInverse(a, 26);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToOutputFile)));
			List<String> allLines = CryptobrickIO.readFile(pathToInputFile);
			
			for (int i = 0; i < allLines.size() - 1; i++)
			{
				writer.write(processLine(allLines.get(i), x, b));
				writer.newLine();
			}
			writer.write(processLine(allLines.get(allLines.size()-1), x, b));
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) 
		{
			throw new CryptobrickException(e);
		}
	}
	
	private static int modularMultiplicativeInverse(int a, int m)
	{
		a %= m;
		for (int x = 1; x < m; x++)
		{
			if ((a*x) % m == 1) return x;
		}
		
		return -1;
	}
	
	private static String processLine(String in, int x, int b)
	{
		String out = "";
		
		in = in.toLowerCase();
		
		char[] characters = in.toCharArray();
		for (char c : characters)
		{
			char newChar = c;
			
			if (newChar >= 'a' && newChar <= 'z')
			{
				System.out.print(newChar);
				newChar -= 'a'-1;
				newChar -= b;
				
				if (newChar <= 0)
					newChar += 26;
				
				newChar *= x;
				
				newChar %= 26;
				if (newChar == 0) newChar = 26;
				
				newChar += 'a'-1;
				System.out.println(" to " + newChar);
			}
			
			out += newChar;
		}
		
		return out;
	}

}
