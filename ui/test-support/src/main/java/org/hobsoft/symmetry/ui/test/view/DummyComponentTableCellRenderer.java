/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/DummyComponentTableCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.view;

import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyComponentTableCellRenderer.java 95278 2011-11-17 15:54:48Z mark@IIZUKA.CO.UK $
 */
public class DummyComponentTableCellRenderer extends DummyComponent implements TableCellRenderer
{
	// TableCellRenderer methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DummyComponent getTableCellComponent(Table table, int row, int column)
	{
		return this;
	}
}
