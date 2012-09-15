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
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.EventFilter;

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code WellFormedEventFilter}.
 * 
 * @author Mark Hobson
 * @see WellFormedEventFilter
 */
public class WellFormedEventFilterTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithAcceptedStartDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		
		assertTrue(filter.accept(createStartDocument()));
	}
	
	@Test
	public void acceptWithAcceptedStartDocumentAcceptsEndDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		filter.accept(createStartDocument());
		
		assertTrue(filter.accept(createEndDocument()));
	}

	@Test
	public void acceptWithRejectedStartDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		
		assertFalse(filter.accept(createStartDocument()));
	}
	
	@Test
	public void acceptWithRejectedStartDocumentRejectsEndDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		filter.accept(createStartDocument());
		
		assertFalse(filter.accept(createEndDocument()));
	}
	
	@Test
	public void acceptWithAcceptedStartElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		
		assertTrue(filter.accept(createStartElement()));
	}
	
	@Test
	public void acceptWithAcceptedStartElementAcceptsEndElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		filter.accept(createStartElement());
		
		assertTrue(filter.accept(createEndElement()));
	}

	@Test
	public void acceptWithRejectedStartElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		
		assertFalse(filter.accept(createStartElement()));
	}
	
	@Test
	public void acceptWithRejectedStartElementRejectsEndElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		filter.accept(createStartElement());
		
		assertFalse(filter.accept(createEndElement()));
	}
}
