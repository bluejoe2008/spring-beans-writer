package cn.bluejoe.xmlbeans.node.creator;

import cn.bluejoe.xmlbeans.node.value.StringValueNode;
import cn.bluejoe.xmlbeans.writer.NodeCreatorContext;

public class StringValueNodeCreator
{
	public StringValueNode createValueNode(Object value, NodeCreatorContext ctx)
	{
		StringValueNode node = new StringValueNode();
		node.setValue(value.toString());
		return node;
	}
}
