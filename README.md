spring-beans-writer
===================

writer for spring IOC beans files

it is too easy to use, please see the test classes.

example codes:

		SpringBeans beans = new SpringBeans();
		beans.addBean(_database, "database");
		beans.addBeans(_persons);

		SpringBeansWriter sbw = new SpringBeansWriter();
		List<FilteredOutput> outputs = new ArrayList<FilteredOutput>();
		outputs.add(new SimpleFilteredOutput(new File("spring_beans.xml")));
		sbw.write(beans, outputs);


you can let spring-beans-writer save beans into multiple files:

		List<FilteredOutput> outputs = new ArrayList<FilteredOutput>();
		//write tables into a spring_persons.xml
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
		
on default, only annotated properties of a JavaBean will be written into output files. To annotate a JavaBean property, use '@XmlProperty':

	@XmlProperty
	public void setName(String name)
	{
		_name = name;
	}
	
you can let spring-beans-writer write all JavaBean properties:

	SpringBeansWriter sbw = new SpringBeansWriter(new DumpAllProperties());
	
that is ALL!

finally, thanks to suxianming@cnic.cn, how have even worked on the project with me.
