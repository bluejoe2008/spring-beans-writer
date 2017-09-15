package cn.bluejoe.xmlbeans.node;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import cn.bluejoe.xmlbeans.node.value.AbstractXmlNode;
import cn.bluejoe.xmlbeans.node.value.ValueNodeDelegate;

/**
 * @author bluejoe2008@gmail.com
 */

public class BeansNode extends AbstractXmlNode implements XmlSerializableNode
{
	List<ValueNodeDelegate> _beanValueNodes = new ArrayList<ValueNodeDelegate>();

	public BeansNode()
	{
	}

	public BeansNode(List<ValueNodeDelegate> beanValueNodes)
	{
		_beanValueNodes.addAll(beanValueNodes);
	}

	public void addBeanNode(ValueNodeDelegate bn)
	{
		_beanValueNodes.add(bn);
	}

	public List<ValueNodeDelegate> getBeanNodes()
	{
		return _beanValueNodes;
	}

	public void writeTo(Element parentElement)
	{
		Element beansElement = parentElement.addElement("beans", "http://www.springframework.org/schema/beans");

		beansElement.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		beansElement.addNamespace("util", "http://www.springframework.org/schema/util");

		beansElement.addAttribute(new QName("schemaLocation", new Namespace("xsi",
				"http://www.w3.org/2001/XMLSchema-instance")), "http://www.springframework.org/schema/beans\r\n\t\t"
				+ "http://www.springframework.org/schema/beans/spring-beans-2.0.xsd\r\n\t\t"
				+ "http://www.springframework.org/schema/util\r\n\t\t"
				+ "http://www.springframework.org/schema/util/spring-util-2.0.xsd");

		for (ValueNodeDelegate beanNode : _beanValueNodes)
		{
			beanNode.writeTo(beansElement);
		}
	}
}
