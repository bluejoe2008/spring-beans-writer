package cn.bluejoe.util;

/**
 * @author bluejoe2008@gmail.com
 */
public class InvalidFieldException extends RuntimeException
{
	private static final long serialVersionUID = 5256983344601667969L;

	private String _message;

	public InvalidFieldException(String message)
	{
		super(message);
		_message = message;
	}

	public String getMessage()
	{
		return _message;
	}
}
