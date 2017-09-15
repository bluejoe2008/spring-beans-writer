package cn.bluejoe.xmlbeans.writer;

import java.io.OutputStream;

import cn.bluejoe.util.Matcher;
import cn.bluejoe.xmlbeans.node.value.EntityNode;

/**
 * @author bluejoe2008@gmail.com
 */
public interface FilteredOutput
{
	public Matcher<EntityNode<?>> getFilter();

	public OutputStream getOutputStream();
}