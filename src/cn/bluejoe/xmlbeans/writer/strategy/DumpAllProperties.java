package cn.bluejoe.xmlbeans.writer.strategy;

import java.beans.PropertyDescriptor;

/**
 * @author bluejoe2008@gmail.com
 */

public class DumpAllProperties extends CacheEnabledBeanWriterFormatFactory
{
	@Override
	boolean accepts(PropertyDescriptor propertyDescriptor)
	{
		return true;
	}
}
