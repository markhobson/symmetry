/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/AbstractBeanTableModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public abstract class AbstractBeanTableModel<T> extends AbstractTableModel implements BeanTableModel<T>
{
	// fields -----------------------------------------------------------------
	
	private BeanInfo beanInfo;
	
	// constructors -----------------------------------------------------------
	
	public AbstractBeanTableModel(Class<?> beanClass)
	{
		this(beanClass, BeanUtils.getBeanInfo(beanClass));
	}
	
	public AbstractBeanTableModel(Class<?> beanClass, BeanInfo beanInfo)
	{
		this.beanInfo = checkBeanInfo(beanInfo, beanClass);
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount()
	{
		return beanInfo.getPropertyDescriptors().length;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column)
	{
		return getPropertyDescriptor(column).getDisplayName();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int column)
	{
		return getPropertyDescriptor(column).getPropertyType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		T bean = getBeanAt(row);
		
		if (bean == null)
		{
			return null;
		}
		
		PropertyDescriptor property = getPropertyDescriptor(column);
		
		return Properties.get(bean, property);
	}
	
	// public methods ---------------------------------------------------------
	
	public Class<T> getBeanClass()
	{
		return (Class<T>) getBeanClass(beanInfo);
	}
	
	public BeanInfo getBeanInfo()
	{
		return beanInfo;
	}
	
	public void setBeanInfo(BeanInfo beanInfo)
	{
		this.beanInfo = checkBeanInfo(beanInfo, getBeanClass());
	}
	
	// protected methods ------------------------------------------------------
	
	protected PropertyDescriptor getPropertyDescriptor(int column)
	{
		return beanInfo.getPropertyDescriptors()[column];
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends BeanInfo> T checkBeanInfo(T beanInfo, Class<?> beanClass)
	{
		checkNotNull(beanInfo, "beanInfo cannot be null");
		checkArgument(beanInfo.getBeanDescriptor() != null, "beanInfo must have a bean descriptor: %s", beanInfo);
		
		Class<?> thatBeanClass = getBeanClass(beanInfo);
		
		checkArgument(beanClass.isAssignableFrom(thatBeanClass), "beanInfo must have a bean class assignable to %s: %s",
			beanClass.getName(), thatBeanClass.getName());
		
		return beanInfo;
	}
	
	private static Class<?> getBeanClass(BeanInfo beanInfo)
	{
		return (Class<?>) beanInfo.getBeanDescriptor().getBeanClass();
	}
}
