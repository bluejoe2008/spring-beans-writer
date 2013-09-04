package cn.bluejoe.xmlbeans.node.creator;

import cn.bluejoe.xmlbeans.node.value.EntityNode;
import cn.bluejoe.xmlbeans.writer.NodeCreatorContext;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

public interface EntityNodeCreator<T>
{
	EntityNode<T> createEntityNode(T value, String nodeId, NodeCreatorContext ctx)
			throws NoSuitableBeanWritterFormatException;

}