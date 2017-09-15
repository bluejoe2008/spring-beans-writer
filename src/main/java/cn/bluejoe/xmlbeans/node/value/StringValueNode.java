package cn.bluejoe.xmlbeans.node.value;

import org.dom4j.Element;

/**
 * @author bluejoe2008@gmail.com
 */
public class StringValueNode extends AbstractXmlNode implements ValueNode
{
	String _value;

	public String getValue()
	{
		return _value;
	}

	public void setValue(String value)
	{
		_value = value;
	}

	public void writeTo(Element parentElement)
	{
		Element valueElement = parentElement.addElement("value");
		valueElement.setText(_value);
	}
}
