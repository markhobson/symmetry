/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ClosureActionTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code ClosureAction}.
 * 
 * @author Mark Hobson
 * @see ClosureAction
 */
@RunWith(JMock.class)
public class ClosureActionTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void actionPerformed()
	{
		@SuppressWarnings("unchecked")
		final Closure<ActionEvent> closure = mockery.mock(Closure.class);
		final ActionEvent event = new ActionEvent(new DummyComponent());

		mockery.checking(new Expectations() { {
			oneOf(closure).apply(event);
		} });
		
		ClosureAction action = new ClosureAction();
		action.setClosure(closure);
		
		action.actionPerformed(event);
	}
}
