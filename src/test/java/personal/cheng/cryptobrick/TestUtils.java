package personal.cheng.cryptobrick;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * General utility tools for unit tests
 * 
 * @author chenga1
 */
public class TestUtils 
{
	/**
	 * Returns true if the two files have exactly the same contents.
	 * 
	 * @param a first file
	 * @param b second file
	 * @return whether the two files are equal
	 */
	public static boolean sameFile(File a, File b)
	{
		try (
				BufferedReader readerA = new BufferedReader(new FileReader(a)); 
				BufferedReader readerB = new BufferedReader(new FileReader(b));  )
		{
			while (readerA.ready())
			{
				String one = readerA.readLine();
				String two = readerB.readLine();
				
				if (one.equals(two) == false)
					return false;
			}

			return true;
		} 
		catch (IOException e) 
		{
			// Don't care about exception because it's for a JUnit test. Just return false
			return false;
		}
	}

	/**
	 * Returns true if the two files have exactly the same contents.
	 * 
	 * @param a path to the first file
	 * @param b path to he second file
	 * @return whether the two files are equal
	 */
	public static boolean sameFile(String a, String b)
	{
		return sameFile(new File(a), new File(b));
	}
}
