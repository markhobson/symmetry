/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
