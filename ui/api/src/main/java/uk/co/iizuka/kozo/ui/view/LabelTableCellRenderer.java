/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/LabelTableCellRenderer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: LabelTableCellRenderer.java 100497 2012-04-16 15:42:05Z mark@IIZUKA.CO.UK $
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
