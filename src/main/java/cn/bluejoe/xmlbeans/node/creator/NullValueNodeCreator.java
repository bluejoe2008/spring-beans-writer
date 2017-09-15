package cn.bluejoe.xmlbeans.node.creator;

import cn.bluejoe.xmlbeans.node.value.NullValueNode;
import cn.bluejoe.xmlbeans.writer.NodeCreatorContext;

/**
 * @author bluejoe2008@gmail.com
 */
public class NullValueNodeCreator
{
	public NullValueNode createValueNode(NodeCreatorContext ctx)
	{
		NullValueNode node = new NullValueNode();
		return node;
	}
}
