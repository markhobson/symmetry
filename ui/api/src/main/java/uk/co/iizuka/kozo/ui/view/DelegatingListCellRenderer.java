/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/DelegatingListCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingListCellRenderer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public abstract class DelegatingListCellRenderer<T> implements ListCellRenderer<T>
{
	// fields -----------------------------------------------------------------
	
	private final ListCellRenderer<T> delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingListCellRenderer(ListCellRenderer<T> delegate)
	{
		if (delegate == null)
		{
			throw new NullPointerException("delegate cannot be null");
		}
		
		this.delegate = delegate;
	}
	
	// ListCellRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getListCellComponent(ComboBox<T> comboBox, int index)
	{
		return delegate.getListCellComponent(comboBox, index);
	}
	
	// protected methods ------------------------------------------------------
	
	protected ListCellRenderer<T> getDelegate()
	{
		return delegate;
	}
}
