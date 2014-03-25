package cn.bluejoe.xmlbeans.node.creator;

import cn.bluejoe.xmlbeans.node.value.ValueNodeDelegate;

/**
 * @author bluejoe2008@gmail.com
 */
public class ValueNodeDelegateCreator
{
	public ValueNodeDelegate createValueNode()
	{
		return new ValueNodeDelegate();
	}
}
