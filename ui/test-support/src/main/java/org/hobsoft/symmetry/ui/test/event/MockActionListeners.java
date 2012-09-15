/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/event/MockActionListeners.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.event;

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.jmock.Expectations;
import org.jmock.Mockery;

/**
 * Factory for mock {@code ActionListener}s.
 * 
 * @author Mark Hobson
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
