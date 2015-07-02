package personal.cheng.cryptobrick.cipher.decrypt;

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

	public static void main(String[] args) 
	{

		// TODO Auto-generated method stub

	}

}
