package cn.bluejoe.vo;

import java.util.Date;
import java.util.List;

import cn.bluejoe.xmlbeans.writer.strategy.XmlProperty;

public class Database
{
	private int _id;

	private String _name;

	private Date _createTime;

	private Person _owner;

	private List<Table> _tables;

	public int getId()
	{
		return _id;
	}

	@XmlProperty
	public void setId(int id)
	{
		_id = id;
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

	public Date getCreateTime()
	{
		return _createTime;
	}

	@XmlProperty
	public void setCreateTime(Date createTime)
	{
		_createTime = createTime;
	}

	public Person getOwner()
	{
		return _owner;
	}

	@XmlProperty
	public void setOwner(Person owner)
	{
		_owner = owner;
	}

	public List<Table> getTables()
	{
		return _tables;
	}

	@XmlProperty
	public void setTables(List<Table> tables)
	{
		_tables = tables;
	}
}
