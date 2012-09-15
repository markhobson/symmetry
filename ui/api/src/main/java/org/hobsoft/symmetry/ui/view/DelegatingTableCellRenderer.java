/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/DelegatingTableCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DelegatingTableCellRenderer implements TableCellRenderer
{
	// fields -----------------------------------------------------------------
	
	private final TableCellRenderer delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingTableCellRenderer(TableCellRenderer delegate)
	{
		if (delegate == null)
		{
			throw new NullPointerException("delegate cannot be null");
		}
		
		this.delegate = delegate;
	}

	// TableCellRenderer methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getTableCellComponent(Table table, int row, int column)
	{
		return delegate.getTableCellComponent(table, row, column);
	}
	
	// protected methods ------------------------------------------------------
	
	protected TableCellRenderer getDelegate()
	{
		return delegate;
	}
}
