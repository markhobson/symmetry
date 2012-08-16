/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/DummyActionListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.event;

import uk.co.iizuka.kozo.ui.event.ActionEvent;
import uk.co.iizuka.kozo.ui.event.ActionListener;

/**
 * Dummy action listener for use in tests.
 * 
 * @author Mark Hobson
 * @version $Id: DummyActionListener.java 99606 2012-03-16 12:46:26Z mark@IIZUKA.CO.UK $
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
