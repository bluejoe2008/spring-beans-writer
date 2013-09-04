package cn.bluejoe.xmlbeans.node.value;

public interface EntityNode<T> extends ValueNode
{
	public String getNodeId();

	public T getValue();

	public void setNodeId(String nodeId);

	public void setValue(T bean);

}