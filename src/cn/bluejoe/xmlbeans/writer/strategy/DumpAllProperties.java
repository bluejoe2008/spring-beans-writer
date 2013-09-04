package cn.bluejoe.xmlbeans.writer.strategy;

import java.beans.PropertyDescriptor;

public class DumpAllProperties extends CacheEnabledBeanWriterFormatFactory
{
	@Override
	boolean accepts(PropertyDescriptor propertyDescriptor)
	{
		return true;
	}
}
