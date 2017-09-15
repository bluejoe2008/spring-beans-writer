package cn.bluejoe.vo;

import cn.bluejoe.xmlbeans.writer.strategy.XmlProperty;

public class Field
{
	private int _id;

	private long _length;

	private String _defaultValue;

	private boolean _nullable;

	public int getId()
	{
		return _id;
	}

	@XmlProperty
	public void setId(int id)
	{
		_id = id;
	}

	public long getLength()
	{
		return _length;
	}

	@XmlProperty
	public void setLength(long length)
	{
		_length = length;
	}

	public String getDefaultValue()
	{
		return _defaultValue;
	}

	@XmlProperty
	public void setDefaultValue(String defaultValue)
	{
		_defaultValue = defaultValue;
	}

	public boolean isNullable()
	{
		return _nullable;
	}

	@XmlProperty
	public void setNullable(boolean nullable)
	{
		_nullable = nullable;
	}
}
