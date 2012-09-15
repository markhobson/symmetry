/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/LabelTableCellRenderer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class LabelTableCellRenderer extends Label implements TableCellRenderer
{
	// constructors -----------------------------------------------------------
	
	public LabelTableCellRenderer()
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
		
		Object value = table.getModel().getValueAt(row, column);
		
		setText(firstNonNull(value, "").toString());
		
		return this;
	}
}
