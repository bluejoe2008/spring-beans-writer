package cn.bluejoe.xmlbeans.node.value;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import cn.bluejoe.xmlbeans.node.PropertyNode;
import cn.bluejoe.xmlbeans.node.XmlSerializableNode;

public class BeanEntityNode extends AbstractXmlNode implements XmlSerializableNode, ValueNode, EntityNode<Object>
{
	String _className;

	String _nodeId;

	List<PropertyNode> _properties = new ArrayList<PropertyNode>();

	private Object _value;

	public void addPropertyNode(PropertyNode pn)
	{
		_properties.add(pn);
	}

	public String getClassName()
	{
		return _className;
	}

	public String getNodeId()
	{
		return _nodeId;
	}

	public Object getValue()
	{
		return _value;
	}

	public void setClassName(String className)
	{
		_className = className;
	}

	public void setNodeId(String id)
	{
		_nodeId = id;
	}

	public void setValue(Object bean)
	{
		_value = bean;
	}

	public String toString()
	{
		if (_nodeId == null)
		{
			return "bean(" + _value + ")";
		}
		else
		{
			return "bean(" + _nodeId + ")";
		}
	}

	public void writeTo(Element parentElement)
	{
		Element beanElement = parentElement.addElement("bean");

		if (_nodeId != null)
		{
			beanElement.addAttribute("id", _nodeId);
		}

		beanElement.addAttribute("class", _className);

		for (PropertyNode propertyNode : _properties)
		{
			propertyNode.writeTo(beanElement);
		}
	}
}
