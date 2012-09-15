/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/DummyTableCellListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.event;

import org.hobsoft.symmetry.ui.event.TableCellListener;
import org.hobsoft.symmetry.ui.event.TableEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DummyTableCellListener implements TableCellListener
{
	// TODO: rename to NullTableCellListener
	
	// TableCellListener methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void tableCellSelected(TableEvent event)
	{
		// no-op
	}
}
