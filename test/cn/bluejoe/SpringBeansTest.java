package cn.bluejoe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.bluejoe.util.Matcher;
import cn.bluejoe.vo.Database;
import cn.bluejoe.vo.Person;
import cn.bluejoe.xmlbeans.SpringBeans;
import cn.bluejoe.xmlbeans.node.value.EntityNode;
import cn.bluejoe.xmlbeans.writer.FilteredOutput;
import cn.bluejoe.xmlbeans.writer.SimpleFilteredOutput;
import cn.bluejoe.xmlbeans.writer.SpringBeansWriter;

public class SpringBeansTest extends BaseTest
{
	@Test
	public void testWriter1() throws Exception
	{
		SpringBeans beans = new SpringBeans();
		beans.addBean(_database, "database");
		beans.addBeans(_persons);

		SpringBeansWriter sbw = new SpringBeansWriter();

		//FIXME BUG，除非节点存在引用，否则无法实现多文件输出
		List<FilteredOutput> outputs = new ArrayList<FilteredOutput>();
		outputs.add(new SimpleFilteredOutput(new File("spring_beans.xml")));
		sbw.write(beans, outputs);

		// 解组
		ApplicationContext context = new FileSystemXmlApplicationContext("spring_beans.xml");
		Database db = (Database) context.getBean("database");
		Assert.assertEquals("cms", db.getName());
		Assert.assertEquals(2, db.getTables().size());
		Assert.assertEquals("teachers", db.getTables().get(0).getName());
	}

	@Test
	public void testWriter2() throws Exception
	{
		SpringBeans beans = new SpringBeans();
		beans.addBean(_database, "database");
		beans.addBeans(_persons);

		SpringBeansWriter sbw = new SpringBeansWriter();

		List<FilteredOutput> outputs = new ArrayList<FilteredOutput>();
		//write tables into a table.xml
		outputs.add(new SimpleFilteredOutput(new Matcher<EntityNode<?>>()
		{
			@Override
			public boolean matches(EntityNode<?> x)
			{
				return x.getValue().getClass() == Person.class;
			}
		}, new File("spring_persons.xml")));

		outputs.add(new SimpleFilteredOutput(new File("spring_databases.xml")));
		sbw.write(beans, outputs);

		// 解组
		ApplicationContext context = new FileSystemXmlApplicationContext("spring_databases.xml", "spring_persons.xml");
		Database db = (Database) context.getBean("database");
		Assert.assertEquals("cms", db.getName());
		Assert.assertEquals(2, db.getTables().size());
		Assert.assertEquals("teachers", db.getTables().get(0).getName());
	}
}
