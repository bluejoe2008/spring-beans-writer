package cn.bluejoe.xmlbeans.node.creator;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;

import cn.bluejoe.xmlbeans.node.PropertyNode;
import cn.bluejoe.xmlbeans.node.value.BeanEntityNode;
import cn.bluejoe.xmlbeans.writer.NodeCreatorContext;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

/**
 * @author bluejoe2008@gmail.com
 */
@SuppressWarnings("rawtypes")
public class BeanEntityNodeCreator implements EntityNodeCreator
{
	public BeanEntityNode createEntityNode(Object bean, String nodeId, NodeCreatorContext ctx)
			throws NoSuitableBeanWritterFormatException
	{
		BeanEntityNode beanNode = new BeanEntityNode();
		beanNode.setNodeId(nodeId);
		beanNode.setValue(bean);
		beanNode.setClassName(bean.getClass().getName());

		for (PropertyDescriptor property : ctx.getBeanPropertySelection(bean))
		{
			String propertyName = property.getName();
			if (!PropertyUtils.isReadable(bean, propertyName))
			{
				continue;
			}

			if (!PropertyUtils.isWriteable(bean, propertyName))
			{
				continue;
			}

			try
			{
				Object propertyValue = PropertyUtils.getProperty(bean, propertyName);
				if (propertyValue != null || ctx.isWriteNullValue())
				{
					PropertyNode pn = new PropertyNode(propertyName);
					pn.setValueNode(ctx.createValueNode(pn, propertyValue));
					beanNode.addPropertyNode(pn);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return beanNode;
	}

}
