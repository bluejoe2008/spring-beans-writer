package cn.bluejoe.xmlbeans.node.value;

import cn.bluejoe.xmlbeans.node.XmlSerializableNode;

public class AbstractXmlNode
{
	private XmlSerializableNode _parentNode;

	public XmlSerializableNode getParentNode()
	{
		return _parentNode;
	}

	public void setParentNode(XmlSerializableNode parentNode)
	{
		_parentNode = parentNode;
	}
}