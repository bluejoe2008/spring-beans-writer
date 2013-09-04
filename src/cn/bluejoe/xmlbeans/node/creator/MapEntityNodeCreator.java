package cn.bluejoe.xmlbeans.node.creator;

import java.util.Map;
import java.util.Map.Entry;

import cn.bluejoe.xmlbeans.node.value.EntityNode;
import cn.bluejoe.xmlbeans.node.value.MapEntityNode;
import cn.bluejoe.xmlbeans.node.value.ValueNode;
import cn.bluejoe.xmlbeans.writer.NodeCreatorContext;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

public class MapEntityNodeCreator implements EntityNodeCreator<Map<?, ?>>
{
	public EntityNode<Map<?, ?>> createEntityNode(Map<?, ?> value, String nodeId, NodeCreatorContext ctx)
			throws NoSuitableBeanWritterFormatException
	{
		MapEntityNode node = new MapEntityNode();
		node.setNodeId(nodeId);
		node.setValue(value);

		for (Entry<?, ?> v : value.entrySet())
		{
			ValueNode kvn = ctx.createValueNode(node, v.getKey());
			ValueNode vvn = ctx.createValueNode(node, v.getValue());
			node.add(kvn, vvn);
		}

		return node;
	}

}
