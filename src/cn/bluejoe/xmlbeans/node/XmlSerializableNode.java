package cn.bluejoe.xmlbeans.node;

import org.dom4j.Element;

/**
 * @author bluejoe2008@gmail.com
 */

public interface XmlSerializableNode
{
	public XmlSerializableNode getParentNode();

	public void writeTo(Element parentElement);
}
