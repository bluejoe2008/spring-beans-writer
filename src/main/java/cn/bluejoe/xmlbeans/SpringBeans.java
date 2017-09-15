package cn.bluejoe.xmlbeans;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bluejoe2008@gmail.com
 */

public class SpringBeans
{
	private Map<Object, String> _beans = new IdentityHashMap<Object, String>();

	public void addBean(Object... beans)
	{
		for (Object bean : beans)
		{
			addBean(bean, null);
		}
	}

	public void addBeans(Object[] beans)
	{
		for (Object bean : beans)
		{
			addBean(bean, null);
		}
	}

	public void addBean(Object bean, String beanId)
	{
		_beans.put(bean, beanId);
	}

	public String getBeanId(Object bean)
	{
		return _beans.get(bean);
	}

	public List<Object> getBeans()
	{
		return new ArrayList<Object>(_beans.keySet());
	}
}