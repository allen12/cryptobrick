package personal.cheng.cryptobrick.util;

public class CryptobrickException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public CryptobrickException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public CryptobrickException(String message) {
		super(message);
	}

	public CryptobrickException(Throwable cause) {
		super(cause);
	}

}
