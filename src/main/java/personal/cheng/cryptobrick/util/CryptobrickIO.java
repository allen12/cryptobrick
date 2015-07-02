package personal.cheng.cryptobrick.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * General utility class for input and output operations.
 * 
 * @author chenga1
 *
 */
public class CryptobrickIO 
{
	/**
	 * Returns a list of all lines of a provided file
	 * @param file
	 * @return
	 */
	public static List<String> readFile(File file)
	{
		return readFile(file.getAbsolutePath());
	}
	
	/**
	 * Returns a list of all lines of a provided file
	 * @param pathToFile
	 * @return
	 */
	public static List<String> readFile(String pathToFile)
	{
		try 
		{
			return Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
			
		} catch (IOException e) 
		{
			throw new CryptobrickException(e);
		}
	}
}
