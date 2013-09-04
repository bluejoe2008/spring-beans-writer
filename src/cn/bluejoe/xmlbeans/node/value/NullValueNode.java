package cn.bluejoe.xmlbeans.node.value;

import org.dom4j.Element;

public class NullValueNode extends AbstractXmlNode implements ValueNode
{
	public void writeTo(Element parentElement)
	{
		parentElement.addElement("null");
	}
}
