/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/state/HtmlEventStateCodecTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.state;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.state.EncodedState;
import uk.co.iizuka.kozo.state.State;
import uk.co.iizuka.kozo.state.StateException;
import uk.co.iizuka.kozo.test.state.StubStateCodec;
import uk.co.iizuka.kozo.ui.html.state.HtmlEventStateCodec.Parameters;

/**
 * Tests {@code HtmlEventStateCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlEventStateCodecTest.java 95586 2011-11-28 12:30:05Z mark@IIZUKA.CO.UK $
 * @see HtmlEventStateCodec
 */
public class HtmlEventStateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private StubStateCodec delegate;
	
	private HtmlEventStateCodec stateCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		delegate = new StubStateCodec();
		
		stateCodec = new HtmlEventStateCodec(delegate);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encodeStateWhenNotEvent() throws StateException
	{
		delegate.setEncodedState(new EncodedState("x"));
		
		assertEquals(new EncodedState("x"), stateCodec.encodeState(createState(false)));
	}
	
	@Test
	public void encodeStateWhenEvent() throws StateException
	{
		delegate.setEncodedState(new EncodedState("x"));
		
		assertEquals(new EncodedState("document.location = 'x'"), stateCodec.encodeState(createState(true)));
	}

	@Test
	public void encodeStateWhenEventNotSpecified() throws StateException
	{
		delegate.setEncodedState(new EncodedState("x"));
		
		assertEquals(new EncodedState("x"), stateCodec.encodeState(new State()));
	}
	
	// private methods --------------------------------------------------------
	
	private static State createState(boolean event)
	{
		State state = new State();
		
		Parameters parameters = new Parameters();
		parameters.setEvent(event);
		state.setParameter("uk.co.iizuka.kozo.ui.html.state.HtmlEventStateCodec$Parameters", parameters);
		
		return state;
	}
}
