package cn.bluejoe.xmlbeans.writer.strategy;

import java.beans.PropertyDescriptor;

/**
 * 
 * @author bluejoe
 * 
 */
public interface BeanPropertySelectionStrategy
{
	PropertyDescriptor[] selectBeanProperties(Object bean) throws NoSuitableBeanWritterFormatException;
}