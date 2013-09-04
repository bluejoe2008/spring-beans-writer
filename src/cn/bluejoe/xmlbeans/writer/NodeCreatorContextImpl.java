package cn.bluejoe.xmlbeans.writer;

import java.beans.PropertyDescriptor;

import cn.bluejoe.xmlbeans.node.XmlSerializableNode;
import cn.bluejoe.xmlbeans.node.value.ValueNode;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

public class NodeCreatorContextImpl implements NodeCreatorContext
{
	public BeansWriterContext _beansWriterContext;

	public NodeCreatorContextImpl(BeansWriterContext beansWriterContext)
	{
		_beansWriterContext = beansWriterContext;
	}

	public void addTopBeanNode(Object bean) throws NoSuitableBeanWritterFormatException
	{
		_beansWriterContext.addTopBeanNode(bean);
	}

	public ValueNode createValueNode(XmlSerializableNode parentNode, Object bean)
			throws NoSuitableBeanWritterFormatException
	{
		return _beansWriterContext.createValueNode(parentNode, bean);
	}

	public PropertyDescriptor[] getBeanPropertySelection(Object bean) throws NoSuitableBeanWritterFormatException
	{
		return _beansWriterContext.getBeanPropertySelection(bean);
	}

	@Override
	public boolean isWriteNullValue()
	{
		return false;
	}
}
