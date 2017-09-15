package cn.bluejoe.xmlbeans.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import cn.bluejoe.util.Matcher;
import cn.bluejoe.xmlbeans.node.value.EntityNode;

/**
 * @author bluejoe2008@gmail.com
 */
public class SimpleFilteredOutput implements FilteredOutput
{
	private Matcher<EntityNode<?>> _matcher;

	private OutputStream _outputStream;

	public SimpleFilteredOutput(File file) throws IOException
	{
		this(new Matcher<EntityNode<?>>()
		{
			public boolean matches(EntityNode<?> toMatch)
			{
				return true;
			}
		}, file);
	}

	public SimpleFilteredOutput(Matcher<EntityNode<?>> matcher, File file) throws IOException
	{
		_matcher = matcher;
		if (!file.exists())
		{
			file.createNewFile();
		}

		_outputStream = new FileOutputStream(file);
	}

	public SimpleFilteredOutput(Matcher<EntityNode<?>> matcher, OutputStream outputStream)
	{
		_matcher = matcher;
		_outputStream = outputStream;
	}

	public SimpleFilteredOutput(OutputStream outputStream)
	{
		this(new Matcher<EntityNode<?>>()
		{

			public boolean matches(EntityNode<?> toMatch)
			{
				return true;
			}
		}, outputStream);
	}

	public Matcher<EntityNode<?>> getFilter()
	{
		return _matcher;
	}

	public OutputStream getOutputStream()
	{
		return _outputStream;
	}

}
