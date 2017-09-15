package cn.bluejoe.xmlbeans.node.value;

import cn.bluejoe.xmlbeans.node.XmlSerializableNode;

/**
 * @author bluejoe2008@gmail.com
 */
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