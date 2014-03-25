package cn.bluejoe.xmlbeans.node.creator;

import java.util.List;

import cn.bluejoe.xmlbeans.node.value.EntityNode;
import cn.bluejoe.xmlbeans.node.value.ListEntityNode;
import cn.bluejoe.xmlbeans.node.value.ValueNode;
import cn.bluejoe.xmlbeans.writer.NodeCreatorContext;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

/**
 * @author bluejoe2008@gmail.com
 */
public class ListEntityNodeCreator implements EntityNodeCreator<List<?>>
{
	public EntityNode<List<?>> createEntityNode(List<?> value, String nodeId, NodeCreatorContext ctx)
			throws NoSuitableBeanWritterFormatException
	{
		ListEntityNode node = new ListEntityNode();
		node.setNodeId(nodeId);
		node.setValue(value);

		for (Object v : value)
		{
			ValueNode vn = ctx.createValueNode(node, v);
			node.add(vn);
		}

		return node;
	}
}
