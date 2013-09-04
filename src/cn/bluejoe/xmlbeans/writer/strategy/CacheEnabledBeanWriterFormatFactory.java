package cn.bluejoe.xmlbeans.writer.strategy;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public abstract class CacheEnabledBeanWriterFormatFactory implements BeanPropertySelectionStrategy
{
	Map<Class<?>, PropertyDescriptor[]> _beanWriterFormats = new HashMap<Class<?>, PropertyDescriptor[]>();

	public PropertyDescriptor[] selectBeanProperties(Object bean) throws NoSuitableBeanWritterFormatException
	{
		try
		{
			Class<?> clazz = bean.getClass();
			if (_beanWriterFormats.containsKey(clazz))
			{
				return _beanWriterFormats.get(clazz);
			}

			PropertyDescriptor[] beanWriter = createBeanWriterFormat(clazz);
			_beanWriterFormats.put(clazz, beanWriter);

			return beanWriter;
		}
		catch (Exception e)
		{
			throw new NoSuitableBeanWritterFormatException(bean, e);
		}
	}

	protected PropertyDescriptor[] createBeanWriterFormat(Class<? extends Object> clazz) throws ClassNotFoundException
	{
		List<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>();

		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors(clazz))
		{
			if (accepts(pd))
			{
				properties.add(pd);
			}
		}

		return properties.toArray(new PropertyDescriptor[0]);
	}

	abstract boolean accepts(PropertyDescriptor propertyDescriptor);
}