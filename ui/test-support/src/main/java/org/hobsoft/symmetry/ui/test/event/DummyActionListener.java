/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/DummyActionListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.event;

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;

/**
 * Dummy action listener for use in tests.
 * 
 * @author Mark Hobson
 */
public class DummyActionListener implements ActionListener
{
	// TODO: rename to NullActionListener
	
	// ActionListener methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		// no-op
	}
}
