package personal.cheng.cryptobrick.util;

import java.io.IOException;

public class CryptobrickException extends RuntimeException
{

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
