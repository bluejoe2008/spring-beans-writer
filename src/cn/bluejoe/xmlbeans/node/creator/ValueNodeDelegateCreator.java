package cn.bluejoe.xmlbeans.node.creator;

import cn.bluejoe.xmlbeans.node.value.ValueNodeDelegate;

public class ValueNodeDelegateCreator
{
	public ValueNodeDelegate createValueNode()
	{
		return new ValueNodeDelegate();
	}
}
