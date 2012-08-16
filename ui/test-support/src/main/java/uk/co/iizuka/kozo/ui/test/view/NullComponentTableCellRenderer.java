/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/NullComponentTableCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.view;

import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.view.TableCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: NullComponentTableCellRenderer.java 99606 2012-03-16 12:46:26Z mark@IIZUKA.CO.UK $
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
