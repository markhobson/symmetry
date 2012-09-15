/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/LabelTableHeaderRenderer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class LabelTableHeaderRenderer extends Label implements TableCellRenderer
{
	// constructors -----------------------------------------------------------
	
	public LabelTableHeaderRenderer()
	{
		setTransient(true);
	}
	
	// TableCellRenderer methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Label getTableCellComponent(Table table, int row, int column)
	{
		checkNotNull(table, "table cannot be null");
		
		Object value = table.getModel().getColumnName(column);
		
		setText(firstNonNull(value, "").toString());
		
		return this;
	}
}
