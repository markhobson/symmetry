/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/DummySelectionListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.event;

import org.hobsoft.symmetry.ui.event.SelectionEvent;
import org.hobsoft.symmetry.ui.event.SelectionListener;

/**
 * 
 * 
 * @author Mark Hobson
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
