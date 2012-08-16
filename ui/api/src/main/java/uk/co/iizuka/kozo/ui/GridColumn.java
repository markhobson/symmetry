/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/GridColumn.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import com.google.common.base.Objects;

import uk.co.iizuka.kozo.ui.layout.Length;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: GridColumn.java 99804 2012-03-22 17:25:54Z mark@IIZUKA.CO.UK $
 */
public final class GridColumn
{
	// TODO: make iterable for column components
	// TODO: add getIndex
	
	// constants --------------------------------------------------------------
	
	private static final Length DEFAULT_WIDTH = null;
	
	// fields -----------------------------------------------------------------
	
	private Length width;
	
	// constructors -----------------------------------------------------------
	
	GridColumn()
	{
		setWidth(DEFAULT_WIDTH);
	}
	
	// public methods ---------------------------------------------------------
	
	public Length getWidth()
	{
		return width;
	}
	
	public void setWidth(Length width)
	{
		this.width = width;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(width);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof GridColumn))
		{
			return false;
		}
		
		GridColumn column = (GridColumn) object;
		
		return Objects.equal(width, column.width);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return String.format("%s[width=%s]", getClass().getName(), width);
	}
}
