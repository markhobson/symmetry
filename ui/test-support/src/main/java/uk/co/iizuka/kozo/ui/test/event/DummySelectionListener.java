/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/DummySelectionListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.event;

import uk.co.iizuka.kozo.ui.event.SelectionEvent;
import uk.co.iizuka.kozo.ui.event.SelectionListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummySelectionListener.java 99606 2012-03-16 12:46:26Z mark@IIZUKA.CO.UK $
 */
public class DummySelectionListener implements SelectionListener
{
	// TODO: rename to NullSelectionListener
	
	// SelectionListener methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void itemSelected(SelectionEvent event)
	{
		// no-op
	}
}
