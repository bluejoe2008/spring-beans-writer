package cn.bluejoe.xmlbeans.node.creator;

import cn.bluejoe.xmlbeans.node.value.BeanRefNode;
import cn.bluejoe.xmlbeans.writer.NodeCreatorContext;

public class BeanRefNodeCreator
{
	public BeanRefNode createValueNode(String nodeId, NodeCreatorContext ctx)
	{
		BeanRefNode node = new BeanRefNode(nodeId);
		return node;
	}
}
