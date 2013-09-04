package cn.bluejoe.xmlbeans.writer;

import java.beans.PropertyDescriptor;

import cn.bluejoe.xmlbeans.node.XmlSerializableNode;
import cn.bluejoe.xmlbeans.node.value.ValueNode;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

public interface NodeCreatorContext
{
	void addTopBeanNode(Object bean) throws NoSuitableBeanWritterFormatException;

	ValueNode createValueNode(XmlSerializableNode parentNode, Object bean) throws NoSuitableBeanWritterFormatException;

	PropertyDescriptor[] getBeanPropertySelection(Object bean) throws NoSuitableBeanWritterFormatException;

	boolean isWriteNullValue();
}