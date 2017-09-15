package cn.bluejoe.xmlbeans.writer.strategy;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author bluejoe2008@gmail.com
 */

public class DumpAnnotatedProperties extends CacheEnabledBeanWriterFormatFactory
{
	@Override
	boolean accepts(PropertyDescriptor propertyDescriptor)
	{
		Method method = propertyDescriptor.getWriteMethod();

		if (method == null)
			return false;

		if (method.getAnnotation(XmlProperty.class) != null)
		{
			return true;
		}

		method = propertyDescriptor.getReadMethod();
		if (method.getAnnotation(XmlProperty.class) != null)
		{
			return true;
		}

		return false;
	}
}
