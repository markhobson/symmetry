/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/DelegatingBeanTableModel.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingBeanTableModel.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the bean type
 */
public abstract class DelegatingBeanTableModel<T> extends DelegatingTableModel implements BeanTableModel<T>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingBeanTableModel(BeanTableModel<T> delegate)
	{
		super(delegate);
	}
	
	// BeanTableModel methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getBeanAt(int row)
	{
		return getDelegate().getBeanAt(row);
	}
	
	// DelegatingTableModel methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeanTableModel<T> getDelegate()
	{
		// guaranteed by constructor
		@SuppressWarnings("unchecked")
		BeanTableModel<T> model = (BeanTableModel<T>) super.getDelegate();
		
		return model;
	}
}
