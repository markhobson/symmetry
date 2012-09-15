/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/transform/EventTransformersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.transform;

import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.hobsoft.symmetry.support.xml.stax.EventTransformer;
import org.junit.Test;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.accept;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.reject;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code EventTransformers}.
 * 
 * @author Mark Hobson
 * @see EventTransformers
 */
public class EventTransformersTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void identity()
	{
		XMLEvent event = createEvent();
		
		assertEventEquals(event, EventTransformers.identity().transform(event));
	}

	@Test
	public void composeTransformers()
	{
		EventTransformer transformer = EventTransformers.compose(new AppendingCharactersTransformer("x"),
			new AppendingCharactersTransformer("y"));
		
		assertCharactersEquals("axy", transformer.transform(createCharacters("a")));
	}
	
	@Test
	public void composeTransformersWithNullTransformer1()
	{
		EventTransformer transformer = new StubEventTransformer();
		
		assertSame(transformer, EventTransformers.compose(null, transformer));
	}
	
	@Test
	public void composeTransformersWithNullTransformer2()
	{
		EventTransformer transformer = new StubEventTransformer();
		
		assertSame(transformer, EventTransformers.compose(transformer, null));
	}
	
	@Test
	public void composeTransformersWithNullTransformers()
	{
		assertSame(EventTransformers.identity(), EventTransformers.compose(null, null));
	}
	
	@Test
	public void composeConditionalWhenFilterAccepts()
	{
		XMLEvent acceptEvent = createCharacters("a");
		EventTransformer acceptTransformer = new StubEventTransformer(acceptEvent);
		EventTransformer rejectTransformer = new StubEventTransformer(createCharacters("b"));
		EventTransformer transformer = EventTransformers.compose(accept(), acceptTransformer, rejectTransformer);
		
		assertEventEquals(acceptEvent, transformer.transform(createEvent()));
	}
	
	@Test
	public void composeConditionalWhenFilterRejects()
	{
		EventTransformer acceptTransformer = new StubEventTransformer(createCharacters("a"));
		XMLEvent rejectEvent = createCharacters("b");
		EventTransformer rejectTransformer = new StubEventTransformer(rejectEvent);
		EventTransformer transformer = EventTransformers.compose(reject(), acceptTransformer, rejectTransformer);
		
		assertEventEquals(rejectEvent, transformer.transform(createEvent()));
	}

	@Test(expected = NullPointerException.class)
	public void composeConditionalWithNullFilter()
	{
		EventTransformers.compose(null, new StubEventTransformer(), new StubEventTransformer());
	}
	
	@Test
	public void composeConditionalWithNullAcceptTransformer()
	{
		XMLEvent event = createCharacters("a");
		EventTransformer transformer = EventTransformers.compose(accept(), null, new StubEventTransformer());
		
		assertSame(event, transformer.transform(event));
	}
	
	@Test
	public void composeConditionalWithNullRejectTransformer()
	{
		XMLEvent event = createCharacters("a");
		EventTransformer transformer = EventTransformers.compose(reject(), new StubEventTransformer(), null);
		
		assertSame(event, transformer.transform(event));
	}
}
