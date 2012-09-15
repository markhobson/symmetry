/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/SelectionEvent.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import org.hobsoft.symmetry.ui.ComboBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class SelectionEvent extends ComponentEvent
{
	// fields -----------------------------------------------------------------
	
	private final int index;

	// constructors -----------------------------------------------------------
	
	// TODO: introduce Selectable or ItemSelectable interface instead of ComboBox for source?
	public SelectionEvent(ComboBox<?> source, int index)
	{
		super(source);
		
		if (index < 0)
		{
			throw new IllegalArgumentException("index must be non-negative: " + index);
		}
		
		this.index = index;
	}
	
	// EventObject methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComboBox<?> getSource()
	{
		return (ComboBox<?>) super.getSource();
	}
	
	// public methods ---------------------------------------------------------
	
	public int getIndex()
	{
		return index;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		
		hashCode = (hashCode * 31) + index;
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof SelectionEvent))
		{
			return false;
		}
		
		SelectionEvent event = (SelectionEvent) object;
		
		return getSource().equals(event.getSource())
			&& index == event.getIndex();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[source=" + getSource() + ", index=" + index + "]";
	}
}
