package personal.cheng.cryptobrick.cipher.polyalphabetic.encrypt;

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
 * In essence, the Vigenère cipher uses a series of different Caesar ciphers for each letter.
 * The input is the plaintext and a keyword. Although the cipher is very
 * easy to understand, it took over three centuries to be able to crack it!
 * 
 * <p>
 * We use the Vigenère Square to encrypt/decrypt. The square looks like this:
 * <br>
 * 	<code>
 * 	&nbsp;&nbsp;&nbsp;A B C D E F ...
 * 	<br>
 * 	<br>A &nbsp;A B C D E F ...
 * 	<br>B &nbsp;B C D E F G ...
 * 	<br>C &nbsp;C D E F G H ...
 * 	<br>D &nbsp;D E F G H I ...
 * 	<br>E &nbsp;E F G H I J ...
 * 	<br>F &nbsp;F G H I J K ...
 * 	<br>...
 * 	</code>
 * 
 * <p>
 * Notice that row A uses a Caesar shift of 0, row B uses a Caesar shift of 1, etc...
 * 
 * <p>
 * Suppose that we wanted to encrypt "Stay here" with the keyword "LIME". We would write
 * each letter of the keyword under each letter of the plaintext, repeating for the entire length.
 * With each column, we examine each letter of the plaintext and keyword, and look at the
 * Vigenère square. The cipherletter can be found corresponding to row of the plainletter
 * and the column of the keyword (or vice versa):
 * 
 * <p>
 * <code> Plaintext: STAYHERE
 * 	<br>  Keyword&nbsp; : LIMELIME
 * 	<br>  Ciphrtext: DBMCSMDI
 * </code>
 * 
 * 
 * @author chenga1
 */
public class EncryptVigenereCipher
{

	private static final Logger log = Logger.getLogger(EncryptVigenereCipher.class);
	private static int index = 0;
	
	public static void main(String[] args)
	{
		if (args.length < 3)
		{
			log.fatal("Usage: java EncryptKeywordCipher <input.txt> <output.txt> <keyword> <opt: remove spaces>");
			return;
		}
		
		boolean rS = false;
		if (args.length > 3)
			rS = Boolean.valueOf(args[3]);
		
		run(args[0], args[1], args[2], rS);
	}
	
	public static void run(String pathToInputFile, String pathToOutputFile, String keyword, boolean removeSpaces)
	{
		try 
		{
			index = 0;
			keyword = keyword.toUpperCase();

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToOutputFile)));
			
			List<String> allLines = CryptobrickIO.readFile(pathToInputFile);
			
			for (int i = 0; i < allLines.size() - 1; i++)
			{
				writer.write(processLine(allLines.get(i), keyword, removeSpaces));
				writer.newLine();
			}
			writer.write(processLine(allLines.get(allLines.size()-1), keyword, removeSpaces));
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) 
		{
			throw new CryptobrickException(e);
		}
	}
	
	private static String processLine(String in, String keyword, boolean removeSpaces)
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
				newChar = (char)((c + keyword.charAt(index) - 2 * 'A') % 26 + 'A');
				index = ++index % keyword.length();
			}
			
			out += newChar;
		}
		
		return out;
	}

}
