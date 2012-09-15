/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/NullComponentTableCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.view;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class NullComponentTableCellRenderer implements TableCellRenderer
{
	// TODO: rename to NullTableCellRenderer
	
	// TableCellRenderer methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getTableCellComponent(Table table, int row, int column)
	{
		return null;
	}
}
