package cn.bluejoe.xmlbeans.node;

import org.dom4j.Element;

public interface XmlSerializableNode
{
	public XmlSerializableNode getParentNode();

	public void writeTo(Element parentElement);
}
