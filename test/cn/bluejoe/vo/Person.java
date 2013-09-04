package cn.bluejoe.vo;

import cn.bluejoe.xmlbeans.writer.strategy.XmlProperty;

public class Person
{
	private int _age;

	private boolean _gender;

	private String _name;

	public int getAge()
	{
		return _age;
	}

	public String getName()
	{
		return _name;
	}

	public boolean isGender()
	{
		return _gender;
	}

	@XmlProperty
	public void setAge(int age)
	{
		_age = age;
	}

	@XmlProperty
	public void setGender(boolean gender)
	{
		_gender = gender;
	}

	@XmlProperty
	public void setName(String name)
	{
		_name = name;
	}

}
