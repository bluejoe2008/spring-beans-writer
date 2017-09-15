package cn.bluejoe.vo;

import java.util.List;

import cn.bluejoe.xmlbeans.writer.strategy.XmlProperty;

public class Table
{
	private char _abbrChar;

	private String _name;

	private int _length;

	private List<Field> _fields;

	private Database _database;

	public char getAbbrChar()
	{
		return _abbrChar;
	}

	@XmlProperty
	public void setAbbrChar(char abbrChar)
	{
		_abbrChar = abbrChar;
	}

	public String getName()
	{
		return _name;
	}

	@XmlProperty
	public void setName(String name)
	{
		_name = name;
	}

	public int getLength()
	{
		return _length;
	}

	@XmlProperty
	public void setLength(int length)
	{
		_length = length;
	}

	public List<Field> getFields()
	{
		return _fields;
	}

	@XmlProperty
	public void setFields(List<Field> fields)
	{
		_fields = fields;
	}

	public Database getDatabase()
	{
		return _database;
	}

	@XmlProperty
	public void setDatabase(Database database)
	{
		_database = database;
	}
}
