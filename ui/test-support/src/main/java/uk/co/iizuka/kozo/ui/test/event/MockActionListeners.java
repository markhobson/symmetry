/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/MockActionListeners.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.event;

import org.jmock.Expectations;
import org.jmock.Mockery;

import uk.co.iizuka.kozo.ui.event.ActionEvent;
import uk.co.iizuka.kozo.ui.event.ActionListener;

/**
 * Factory for mock {@code ActionListener}s.
 * 
 * @author Mark Hobson
 * @version $Id: MockActionListeners.java 95269 2011-11-17 15:45:14Z mark@IIZUKA.CO.UK $
 */
public final class MockActionListeners
{
	// constructors -----------------------------------------------------------
	
	private MockActionListeners()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static ActionListener createMockActionListener(Mockery mockery, boolean expected)
	{
		final ActionListener mockListener = mockery.mock(ActionListener.class);
		
		if (expected)
		{
			mockery.checking(new Expectations() { {
				oneOf(mockListener).actionPerformed(with(any(ActionEvent.class)));
			} });
		}

		return mockListener;
	}
}
