/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/FakeBeanTableModel.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.beans.BeanInfo;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public class FakeBeanTableModel<T> extends AbstractBeanTableModel<T>
{
	// constructors -----------------------------------------------------------
	
	public FakeBeanTableModel(Class<T> beanClass)
	{
		super(beanClass);
	}
	
	public FakeBeanTableModel(Class<T> beanClass, BeanInfo beanInfo)
	{
		super(beanClass, beanInfo);
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return 0;
	}
	
	// BeanTableModel methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getBeanAt(int row)
	{
		return null;
	}
}
