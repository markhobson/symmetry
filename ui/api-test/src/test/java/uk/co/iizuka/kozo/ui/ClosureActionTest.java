/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ClosureActionTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.ui.event.ActionEvent;
import uk.co.iizuka.kozo.ui.functor.Closure;
import uk.co.iizuka.kozo.ui.test.DummyComponent;

/**
 * Tests {@code ClosureAction}.
 * 
 * @author Mark Hobson
 * @version $Id: ClosureActionTest.java 95330 2011-11-18 12:00:44Z mark@IIZUKA.CO.UK $
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
