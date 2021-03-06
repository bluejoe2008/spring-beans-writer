package cn.bluejoe.xmlbeans.node.value;

import org.dom4j.Element;

/**
 * @author bluejoe2008@gmail.com
 */
public class BeanRefNode extends AbstractXmlNode implements ValueNode
{
	String _nodeId;

	public BeanRefNode(String nodeId)
	{
		_nodeId = nodeId;
	}

	public String getNodeId()
	{
		return _nodeId;
	}

	public void setNodeId(String nodeId)
	{
		_nodeId = nodeId;
	}

	public String toString()
	{
		return "bean-ref(" + _nodeId + ")";
	}

	public void writeTo(Element parentElement)
	{
		Element valueElement = parentElement.addElement("ref");
		valueElement.addAttribute("bean", _nodeId);
	}
}
