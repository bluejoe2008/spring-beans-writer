package cn.bluejoe.xmlbeans.writer;

import java.util.List;

import cn.bluejoe.xmlbeans.node.value.ValueNodeDelegate;

/**
 * @author bluejoe2008@gmail.com
 */
public class BeanNodesNotWritten extends Exception
{
	private static final long serialVersionUID = 6699072065284790625L;

	private List<ValueNodeDelegate> _beanNodes;

	public BeanNodesNotWritten(List<ValueNodeDelegate> beanNodes)
	{
		_beanNodes = beanNodes;
	}

	public String getMessage()
	{
		return String.format("bean nodes not written: %s", _beanNodes);
	}

}
