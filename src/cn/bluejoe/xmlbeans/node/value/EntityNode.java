package cn.bluejoe.xmlbeans.node.value;

/**
 * @author bluejoe2008@gmail.com
 */
public interface EntityNode<T> extends ValueNode
{
	public String getNodeId();

	public T getValue();

	public void setNodeId(String nodeId);

	public void setValue(T bean);

}