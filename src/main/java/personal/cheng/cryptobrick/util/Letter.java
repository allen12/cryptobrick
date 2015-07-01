package personal.cheng.cryptobrick.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A representation of an ASCII character, not limited to just alphanumeric
 * characters as the name suggests.
 * 
 * @author Cheng, Allen
 */
public class Letter implements Comparable<Letter>
{
	private Character letter;
	private Double frequency;
	
	public Letter(char l)
	{
		letter = l;
	}
	
	public Letter(char l, double f)
	{
		this(l);
		frequency = f;
	}
	
	public Character getLetter()
	{
		return letter;
	}
	
	public Double getFrequency()
	{
		return frequency;
	}

	/**
	 * Add to the frequency count of this letter by <code>amt</code>.
	 * @param amt
	 */
	public void addToFrequency(double amt)
	{
		frequency += amt;
	}
	
	/**
	 * Increases the frequency count of this letter by 1.
	 */
	public void incrementFrequency()
	{
		addToFrequency(1.0);
	}
	
	/**
	 * A second object is considered equivalent to
	 * this <code>Letter</code> if:
	 * <br>
	 * <ul>
	 * 	<li> The second object is a <code>Letter</code> </li>
	 * 	<li> Their frequencies are approximately equal, i.e. within 1e-6 of each other, 
	 * 			if the frequencies for both <code>Letter</code>s are defined </li>
	 * 	<li> Their ASCII character representations are the same </li>
	 * </ul>
	 */
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof Letter == false)
			return false;
		
		Letter o = (Letter)other;
		
		if (frequency != null && o.getFrequency() != null && Math.abs(frequency - o.getFrequency()) < 1e-6)
			return false;
		
		if (letter != o.getLetter())
			return false;
		
		return true;
	}
	
	public int compareTo(Letter o) 
	{
		if (frequency != null && o.getFrequency() != null)
			return frequency.compareTo(o.getFrequency());
		
		return letter.compareTo(o.getLetter());
	}
	
	/**
	 * <p>
	 * Returns the relative frequency of a specified letter a-z in
	 * the English language as a percentage accurate to three decimal places.
	 * Returns -1.0 if <code>('A' < letter < 'Z') || ('a' < letter < 'z) == false</code>
	 * </p>
	 * 
	 * <p>
	 * Data obtained from Wikipedia:
	 * https://en.wikipedia.org/wiki/Letter_frequency
	 * </p>
	 * 
	 * @param letter
	 * @return percentage
	 */
	public static double getFrequencyOfLetter(char letter)
	{
		// If letter is lowercase, convert to uppercase
		if (letter >= 'a' && letter <= 'z')
			letter -= ('a' - 'A');
		
		switch (letter)
		{
		case 'A': return 8.167;
		case 'B': return 1.492;
		case 'C': return 2.782;
		case 'D': return 4.253;
		case 'E': return 12.702;
		case 'F': return 2.228;
		case 'G': return 2.015;
		case 'H': return 6.094;
		case 'I': return 6.966;
		case 'J': return 0.153;
		case 'K': return 0.772;
		case 'L': return 4.025;
		case 'M': return 2.406;
		case 'N': return 6.749;
		case 'O': return 7.507;
		case 'P': return 1.929;
		case 'Q': return 0.095;
		case 'R': return 5.987;
		case 'S': return 6.327;
		case 'T': return 9.056;
		case 'U': return 2.758;
		case 'V': return 0.978;
		case 'W': return 2.361;
		case 'X': return 0.150;
		case 'Y': return 1.974;
		case 'Z': return 0.074;
		}
		
		return -1.0;
	}
	
	/**
	 * Returns a list of <code>Letter</code> objects representing the 
	 * A-Z alphabet, sorted in order of decreasing relative frequency
	 * in the English alphabet.
	 * 
	 * @return list
	 */
	public static List<Letter> getOrderedLetters()
	{
		List<Letter> list = new ArrayList<>();
		
		for (char c = 'A'; c <= 'Z'; c++)
		{
			list.add( new Letter(c, Letter.getFrequencyOfLetter(c)) );
		}
		
		Collections.sort(list);
		
		return list;
	}
}
