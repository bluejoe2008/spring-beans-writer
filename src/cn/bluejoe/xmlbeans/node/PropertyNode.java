package cn.bluejoe.xmlbeans.node;

import org.dom4j.Element;

import cn.bluejoe.xmlbeans.node.value.AbstractXmlNode;
import cn.bluejoe.xmlbeans.node.value.BeanRefNode;
import cn.bluejoe.xmlbeans.node.value.StringValueNode;
import cn.bluejoe.xmlbeans.node.value.ValueNode;
import cn.bluejoe.xmlbeans.node.value.ValueNodeDelegate;

public class PropertyNode extends AbstractXmlNode implements XmlSerializableNode
{
	String _name;

	ValueNode _valueNode;

	public PropertyNode(String name)
	{
		_name = name;
	}

	public void setValueNode(ValueNode valueNode)
	{
		_valueNode = valueNode;
	}

	public void writeTo(Element parentElement)
	{
		Element propertyElement = parentElement.addElement("property");
		propertyElement.addAttribute("name", _name);
		writeValue(propertyElement, _valueNode);
	}

	private void writeValue(Element propertyElement, ValueNode valueNode)
	{
		if (valueNode instanceof StringValueNode)
		{
			propertyElement.addAttribute("value", ((StringValueNode) valueNode).getValue());

			return;
		}

		if (valueNode instanceof ValueNodeDelegate)
		{
			ValueNodeDelegate dvn = (ValueNodeDelegate) valueNode;
			ValueNode vn = dvn.getDelegatedValueNode();

			if (vn instanceof BeanRefNode)
			{
				propertyElement.addAttribute("ref", ((BeanRefNode) vn).getNodeId());

				return;
			}
		}

		_valueNode.writeTo(propertyElement);
	}
}
