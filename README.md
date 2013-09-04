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


you can let spring-beans-writer to save beans into multiple files:

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
