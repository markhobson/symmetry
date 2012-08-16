/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/DelegatingListModel.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingListModel.java 99065 2012-03-08 13:00:14Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public abstract class DelegatingListModel<T> implements ListModel<T>
{
	// fields -----------------------------------------------------------------
	
	private final ListModel<T> delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingListModel(ListModel<T> delegate)
	{
		this.delegate = checkNotNull(delegate, "delegate cannot be null");
	}
	
	// ListModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getItem(int index)
	{
		return delegate.getItem(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getItemCount()
	{
		return delegate.getItemCount();
	}
	
	// public methods ---------------------------------------------------------
	
	public ListModel<T> getDelegate()
	{
		return delegate;
	}
}
