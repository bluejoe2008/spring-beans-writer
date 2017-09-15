package cn.bluejoe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import cn.bluejoe.vo.Database;
import cn.bluejoe.vo.Field;
import cn.bluejoe.vo.Person;
import cn.bluejoe.vo.Table;

public class BaseTest
{
	protected Database _database;

	protected Person[] _persons = new Person[2];

	@Before
	public void init()
	{
		_database = new Database();
		_database.setId(1);
		_database.setName("cms");
		_database.setCreateTime(new Date());

		List<Table> tables = new ArrayList<Table>();
		_database.setTables(tables);

		Table teacherTable = new Table();
		teacherTable.setName("teachers");
		teacherTable.setAbbrChar('T');
		teacherTable.setLength(800);
		teacherTable.setDatabase(_database);

		List<Field> teacherTableFields = new ArrayList<Field>();
		teacherTable.setFields(teacherTableFields);

		Table studentTable = new Table();
		studentTable.setName("students");
		studentTable.setAbbrChar('S');
		studentTable.setLength(1000);
		studentTable.setDatabase(_database);

		List<Field> studentTableFields = new ArrayList<Field>();
		studentTable.setFields(studentTableFields);

		tables.add(teacherTable);
		tables.add(studentTable);

		Field teacherName = new Field();
		teacherName.setDefaultValue("");
		teacherName.setId(1);
		teacherName.setLength(40);
		teacherName.setNullable(false);

		Field teacherEducation = new Field();
		teacherEducation.setDefaultValue("");
		teacherEducation.setId(2);
		teacherEducation.setLength(1000);
		teacherEducation.setNullable(true);

		teacherTableFields.add(teacherName);
		teacherTableFields.add(teacherEducation);

		Field studentName = new Field();
		studentName.setDefaultValue("");
		studentName.setId(1);
		studentName.setLength(40);
		studentName.setNullable(false);

		Field studentGender = new Field();
		studentGender.setDefaultValue("男");
		studentGender.setId(1);
		studentGender.setLength(10);
		studentGender.setNullable(false);

		studentTableFields.add(studentName);
		studentTableFields.add(studentGender);

		_persons[0] = new Person();
		_persons[0].setName("bluejoe");
		_persons[0].setAge(35);
		_persons[0].setGender(true);

		_persons[1] = new Person();
		_persons[1].setName("suxianming");
		_persons[1].setAge(30);
		_persons[1].setGender(true);

		_database.setOwner(_persons[0]);
	}
}
