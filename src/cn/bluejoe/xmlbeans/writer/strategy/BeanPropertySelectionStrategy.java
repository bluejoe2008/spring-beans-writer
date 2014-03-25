package cn.bluejoe.xmlbeans.writer.strategy;

import java.beans.PropertyDescriptor;

/**
 * @author bluejoe2008@gmail.com
 */

public interface BeanPropertySelectionStrategy
{
	PropertyDescriptor[] selectBeanProperties(Object bean) throws NoSuitableBeanWritterFormatException;
}