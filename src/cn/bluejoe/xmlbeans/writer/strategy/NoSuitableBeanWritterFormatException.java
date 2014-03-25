package cn.bluejoe.xmlbeans.writer.strategy;

/**
 * @author bluejoe2008@gmail.com
 */

public class NoSuitableBeanWritterFormatException extends RuntimeException
{
	private static final long serialVersionUID = 2851632718971474438L;

	private Object _bean;

	public NoSuitableBeanWritterFormatException(Object bean)
	{
		_bean = bean;
	}

	public NoSuitableBeanWritterFormatException(Object bean, Throwable cause)
	{
		super(cause);
		_bean = bean;
	}

	public String getMessage()
	{
		return "No BeanWritter found with " + "\"" + _bean.getClass().getName() + "\"";
	}
}
