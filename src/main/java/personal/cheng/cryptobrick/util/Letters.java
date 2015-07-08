package personal.cheng.cryptobrick.util;

/**
 * General utility class containing methods that manipulate
 * sequences of letters i.e. strings.
 * 
 * @author Cheng, Allen
 *
 */
public class Letters 
{
	/**
	 * Naive-algorithm to remove case-sensitive duplicates from a given string.
	 * The order of the letters remains the same.
	 * 
	 * @param word the word to analyze
	 * @return the string
	 */
	public static String removeDuplicates(String word)
	{
		String retStr = "";
		
		while (word.equals("") == false)
		{
			char c = word.charAt(0);
			
			retStr += c;
			
			word = word.replace(Character.toString(c), "");
		}
		
		return retStr;
	}

}
