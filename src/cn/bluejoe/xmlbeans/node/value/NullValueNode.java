package cn.bluejoe.xmlbeans.node.value;

import org.dom4j.Element;

/**
 * @author bluejoe2008@gmail.com
 */
public class NullValueNode extends AbstractXmlNode implements ValueNode
{
	public void writeTo(Element parentElement)
	{
		parentElement.addElement("null");
	}
}
