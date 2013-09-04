package cn.bluejoe.xmlbeans.node.value;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class MapEntityNode extends AbstractXmlNode implements ValueNode, EntityNode<Map<?, ?>>
{
	String _nodeId;

	private Map<?, ?> _value;

	Map<ValueNode, ValueNode> _valueNodeMap = new LinkedHashMap<ValueNode, ValueNode>();

	public void add(ValueNode keyValueNode, ValueNode valueValueNode)
	{
		_valueNodeMap.put(keyValueNode, valueValueNode);
	}

	public String getNodeId()
	{
		return _nodeId;
	}

	public Map<?, ?> getValue()
	{
		return _value;
	}

	public void setNodeId(String nodeId)
	{
		_nodeId = nodeId;
	}

	public void setValue(Map<?, ?> bean)
	{
		_value = bean;
	}

	private void writeKey(Element entryElement, ValueNode valueNode)
	{
		if (valueNode instanceof StringValueNode)
		{
			entryElement.addAttribute("key", ((StringValueNode) valueNode).getValue());

			return;
		}

		if (valueNode instanceof ValueNodeDelegate)
		{
			ValueNodeDelegate dvn = (ValueNodeDelegate) valueNode;
			ValueNode vn = dvn.getDelegatedValueNode();

			if (vn instanceof BeanRefNode)
			{
				entryElement.addAttribute("key-ref", ((BeanRefNode) vn).getNodeId());

				return;
			}
		}

		Element keyElement = entryElement.addElement("key");
		valueNode.writeTo(keyElement);
	}

	public void writeTo(Element parentElement)
	{
		Element valueElement;

		if (_nodeId != null)
		{
			valueElement = parentElement.addElement(new QName("map", new Namespace("util",
					"http://www.springframework.org/schema/util")));

			valueElement.addAttribute("id", _nodeId);

			String className = _value.getClass().getName();
			if (!className.startsWith("java.util."))
			{
				valueElement.addAttribute("map-class", className);
			}
		}
		else
		{
			valueElement = parentElement.addElement("map");
		}

		for (Entry<ValueNode, ValueNode> v : _valueNodeMap.entrySet())
		{
			Element entryElement = valueElement.addElement("entry");

			writeKey(entryElement, v.getKey());
			writeValue(entryElement, v.getValue());
		}
	}

	private void writeValue(Element entryElement, ValueNode vvn)
	{
		if (vvn instanceof StringValueNode)
		{
			entryElement.addAttribute("value", ((StringValueNode) vvn).getValue());

			return;
		}

		if (vvn instanceof ValueNodeDelegate)
		{
			ValueNodeDelegate dvn = (ValueNodeDelegate) vvn;
			ValueNode vn = dvn.getDelegatedValueNode();

			if (vn instanceof BeanRefNode)
			{
				entryElement.addAttribute("value-ref", ((BeanRefNode) vn).getNodeId());

				return;
			}
		}

		vvn.writeTo(entryElement);
	}
}
