package cn.bluejoe.util;

public class InvalidGetterMethodException extends RuntimeException
{
	private static final long serialVersionUID = 9160196117344820016L;

	private String _message;

	public InvalidGetterMethodException(String message)
	{
		super(message);
		_message = message;
	}

	public String getMessage()
	{
		return _message;
	}
}
