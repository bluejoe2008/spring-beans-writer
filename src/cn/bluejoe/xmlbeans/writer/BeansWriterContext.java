package cn.bluejoe.xmlbeans.writer;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.bluejoe.xmlbeans.SpringBeans;
import cn.bluejoe.xmlbeans.formater.ValueFormater;
import cn.bluejoe.xmlbeans.formater.ValueFormaterFactory;
import cn.bluejoe.xmlbeans.node.BeansNode;
import cn.bluejoe.xmlbeans.node.XmlSerializableNode;
import cn.bluejoe.xmlbeans.node.creator.BeanEntityNodeCreator;
import cn.bluejoe.xmlbeans.node.creator.BeanRefNodeCreator;
import cn.bluejoe.xmlbeans.node.creator.ListEntityNodeCreator;
import cn.bluejoe.xmlbeans.node.creator.MapEntityNodeCreator;
import cn.bluejoe.xmlbeans.node.creator.NullValueNodeCreator;
import cn.bluejoe.xmlbeans.node.creator.StringValueNodeCreator;
import cn.bluejoe.xmlbeans.node.creator.ValueNodeDelegateCreator;
import cn.bluejoe.xmlbeans.node.value.AbstractXmlNode;
import cn.bluejoe.xmlbeans.node.value.BeanRefNode;
import cn.bluejoe.xmlbeans.node.value.EntityNode;
import cn.bluejoe.xmlbeans.node.value.ValueNode;
import cn.bluejoe.xmlbeans.node.value.ValueNodeDelegate;
import cn.bluejoe.xmlbeans.writer.strategy.BeanPropertySelectionStrategy;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

/**
 * 
 * @author bluejoe
 * 
 */
public class BeansWriterContext
{
	class EntityRefPair
	{
		private List<ValueNodeDelegate> _delegatingNodes = new ArrayList<ValueNodeDelegate>();

		private EntityNode<?> _entityNode;

		private BeanRefNode _refNode;
	}

	SpringBeans _beans;

	BeansNode _beansNode;

	private BeanPropertySelectionStrategy _beanPropertySelectionStrategy;

	private ValueFormaterFactory _valueFormaterFactory;

	Map<Object, EntityRefPair> _referenceMap = new IdentityHashMap<Object, EntityRefPair>();

	long _serial = 0;

	public BeansWriterContext(SpringBeans beans, BeanPropertySelectionStrategy beanPropertySelectionStrategy,
			ValueFormaterFactory valueFormaterFactory)
	{
		_beans = beans;
		_beansNode = new BeansNode();
		_beanPropertySelectionStrategy = beanPropertySelectionStrategy;
		_valueFormaterFactory = valueFormaterFactory;
	}

	public void addTopBeanNode(Object bean) throws NoSuitableBeanWritterFormatException
	{
		_beansNode.addBeanNode((ValueNodeDelegate) createValueNode(_beansNode, bean));
	}

	private String createBeanId()
	{
		String beanId = "bean" + _serial;
		_serial++;

		return beanId;
	}

	private NodeCreatorContext createChildContext(Object childValue)
	{
		return new NodeCreatorContextImpl(this);
	}

	private EntityNode<?> createEntityNode(Object value, String nodeId, NodeCreatorContext ctx)
			throws NoSuitableBeanWritterFormatException
	{
		if (value instanceof List)
		{
			return new ListEntityNodeCreator().createEntityNode((List<?>) value, nodeId, ctx);
		}

		if (value instanceof Map)
		{
			return new MapEntityNodeCreator().createEntityNode((Map<?, ?>) value, nodeId, ctx);
		}

		return new BeanEntityNodeCreator().createEntityNode(value, nodeId, ctx);
	}

	private void createEntityRefPair(Object value, NodeCreatorContext childContext, ValueNodeDelegate node)
			throws NoSuitableBeanWritterFormatException
	{
		EntityRefPair entityRefPair = new EntityRefPair();
		entityRefPair._delegatingNodes.add(node);

		_referenceMap.put(value, entityRefPair);

		String nodeId = getAssignedBeanId(value);

		entityRefPair._entityNode = createEntityNode(value, nodeId, childContext);

		entityRefPair._refNode = new BeanRefNodeCreator().createValueNode(nodeId, childContext);
	}

	private ValueNodeDelegate createObjectValueNode(Object bean, NodeCreatorContext childContext)
			throws NoSuitableBeanWritterFormatException
	{
		ValueNodeDelegate node = new ValueNodeDelegateCreator().createValueNode();

		// if not expanded, now expand it
		if (!_referenceMap.containsKey(bean))
		{
			createEntityRefPair(bean, childContext, node);
		}
		else
		{
			EntityRefPair beanNodeReferences = _referenceMap.get(bean);
			beanNodeReferences._delegatingNodes.add(node);
		}

		return node;
	}

	public ValueNode createValueNode(XmlSerializableNode parentNode, Object bean)
			throws NoSuitableBeanWritterFormatException
	{
		ValueNode node = doCreateValueNode(parentNode, bean);
		((AbstractXmlNode) node).setParentNode(parentNode);

		return node;
	}

	private ValueNode doCreateValueNode(XmlSerializableNode parentNode, Object bean)
			throws NoSuitableBeanWritterFormatException
	{
		NodeCreatorContext childContext = createChildContext(bean);

		// it is a null object
		if (bean == null)
		{
			return new NullValueNodeCreator().createValueNode(childContext);
		}

		// write the object as a text
		ValueFormater pe = getValueFormatter(bean);
		if (pe != null)
		{
			bean = pe.getAsText(bean);
		}

		// primitive objects
		if (bean instanceof Boolean || bean instanceof String || bean instanceof Number || bean instanceof Character
				|| bean instanceof Enum)
		{
			return new StringValueNodeCreator().createValueNode(bean.toString(), childContext);
		}

		// an object, may be reused
		return createObjectValueNode(bean, childContext);
	}

	private String getAssignedBeanId(Object bean)
	{
		return _beans.getBeanId(bean);
	}

	public PropertyDescriptor[] getBeanPropertySelection(Object bean) throws NoSuitableBeanWritterFormatException
	{
		return _beanPropertySelectionStrategy.selectBeanProperties(bean);
	}

	private ValueFormater getValueFormatter(Object value)
	{
		if (_valueFormaterFactory == null)
			return null;

		return _valueFormaterFactory.getValueFormater(value);
	}

	public BeansNode getResolvedBeansNode() throws NoSuitableBeanWritterFormatException
	{
		for (Entry<Object, EntityRefPair> e : _referenceMap.entrySet())
		{
			resolveReference(e.getKey(), e.getValue());
		}

		return _beansNode;
	}

	private void resolveReference(Object object, EntityRefPair beanNodeReferences)
	{
		EntityNode<?> bvn = beanNodeReferences._entityNode;
		BeanRefNode brvn = beanNodeReferences._refNode;

		List<ValueNodeDelegate> delegatingNodes = beanNodeReferences._delegatingNodes;

		// not reused
		if (delegatingNodes.size() == 1)
		{
			delegatingNodes.get(0).setDelegatedValueNode(bvn);
			return;
		}

		// assigns reused beans ids
		String beanId = bvn.getNodeId();

		// has not a name yet
		if (beanId == null)
		{
			beanId = createBeanId();
		}

		bvn.setNodeId(beanId);
		brvn.setNodeId(beanId);

		// find top level bean nodes
		List<ValueNodeDelegate> topBeanNodes = new ArrayList<ValueNodeDelegate>();
		for (ValueNodeDelegate node : delegatingNodes)
		{
			if (node.getParentNode() == _beansNode)
			{
				topBeanNodes.add(node);
			}

			node.setDelegatedValueNode(brvn);
		}

		// there is no nodes at top level
		if (topBeanNodes.isEmpty())
		{
			// create a top level bean
			ValueNodeDelegate node = new ValueNodeDelegateCreator().createValueNode();
			node.setDelegatedValueNode(bvn);
			_beansNode.addBeanNode(node);
		}
		else
		{
			// set the first bean as an entity
			topBeanNodes.get(0).setDelegatedValueNode(bvn);
		}
	}
}
