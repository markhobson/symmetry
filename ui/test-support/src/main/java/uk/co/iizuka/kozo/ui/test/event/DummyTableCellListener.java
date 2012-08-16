/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/DummyTableCellListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.event;

import uk.co.iizuka.kozo.ui.event.TableCellListener;
import uk.co.iizuka.kozo.ui.event.TableEvent;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyTableCellListener.java 99606 2012-03-16 12:46:26Z mark@IIZUKA.CO.UK $
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
