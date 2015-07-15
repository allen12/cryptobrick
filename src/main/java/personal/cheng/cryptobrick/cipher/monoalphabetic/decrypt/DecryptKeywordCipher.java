package personal.cheng.cryptobrick.cipher.monoalphabetic.decrypt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import personal.cheng.cryptobrick.cipher.monoalphabetic.encrypt.EncryptKeywordCipher;
import personal.cheng.cryptobrick.util.CryptobrickException;
import personal.cheng.cryptobrick.util.CryptobrickIO;
import personal.cheng.cryptobrick.util.Letter;
import personal.cheng.cryptobrick.util.Letters;

/**
 * <p>
 * A keyword cipher uses a predefined keyword and a keyletter.
 * </p>
 * 
 * <p>
 * Firstly, repeats of letters in the keyword are removed, and then
 * the cipher alphabet is generated with the keyword matching to
 * the keyletter until the keyword is used up. Then, the rest
 * of the ciphertext letters are used in alphabetical order skipping
 * those already in the keyword.
 * </p>
 * 
 * <p>
 * As an example, consider the keyword <code>FOOBARBAZ</code> and the 
 * keyletter <code>W</code>. Firstly, duplicate letters are removed, 
 * leaving <code>FOBARZ</code>. Then, the letters in this new string
 * are matched to the plaintext alphabet in order, starting from the
 * keyletter <code>W</code>: F ~ W, O ~ X, B ~ Y, A ~ Z, R ~ A, Z ~ B.
 * Starting from C, the rest of the alphabet is written in order,
 * skipping any letters contained in <code>FOBARZ</code>.
 * </p>
 * 
 * <p>
 * Plaintext:&nbsp;&nbsp;<code> A B C D E F G H I J K L M N O P Q R S T U V W X Y Z </code><br>
 * Ciphertext:           <code> R Z C D E G H I J K L M N P Q S T U V W X Y F O B A </code>
 * </p>
 * 
 * @author Cheng, Allen
 */
public class DecryptKeywordCipher 
{
	private static final Logger log = Logger.getLogger(DecryptKeywordCipher.class);
	
	public static void main(String[] args) 
	{
		if (args.length < 4)
		{
			log.fatal("Usage: java DecryptKeywordCipher <input.txt> <output.txt> <keyword> <keyletter>");
			return;
		}
		
		DecryptKeywordCipher.run(args[0], args[1], args[2], args[3]);
	}
	
	public static void run(String pathToInputFile, String pathToOutputFile, String keyword, String keyletter)
	{
		try 
		{
			keyletter = keyletter.toLowerCase();
			String keywordNoDuplicates = Letters.removeDuplicates(keyword);
			Map<Character, Character> mappings = getAlphabetMappings(keywordNoDuplicates, keyletter);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToOutputFile)));
			
			List<String> allLines = CryptobrickIO.readFile(pathToInputFile);
			
			for (int i = 0; i < allLines.size() - 1; i++)
			{
				writer.write(processLine(allLines.get(i), mappings));
				writer.newLine();
			}
			
			writer.write(processLine(allLines.get(allLines.size()-1), mappings));
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) 
		{
			throw new CryptobrickException(e);
		}
	}
	
	private static String processLine(String in, Map<Character, Character> mappings)
	{
		String out = "";
		
		in = in.toLowerCase();
		
		char[] characters = in.toCharArray();
		for (char c : characters)
		{
			char newChar = c;
			
			if (newChar >= 'a' && newChar <= 'z')
			{
				newChar = mappings.get(newChar);
			}
			
			out += newChar;
		}
		
		return out;
	}
	
	// Keys are ciphertext letters, values are plaintext letters
	private static Map<Character, Character> getAlphabetMappings(String keywordNoDup, String keyLetter)
	{
		keyLetter = keyLetter.toUpperCase();
		
		Map<Character, Character> map = EncryptKeywordCipher.getAlphabetMappings(keywordNoDup, keyLetter);
		Map<Character, Character> retMap = new HashMap<>();
		
		for (Map.Entry<Character, Character> entry : map.entrySet())
		{
			retMap.put(Letter.convert(entry.getValue()), Letter.convert(entry.getKey()));
		}
		
		return retMap;
	}
}
