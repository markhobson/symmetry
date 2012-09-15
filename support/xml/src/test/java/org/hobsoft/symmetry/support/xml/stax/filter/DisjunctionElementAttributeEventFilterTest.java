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

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.accept;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.reject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code DisjunctionElementAttributeEventFilter}.
 * 
 * @author Mark Hobson
 * @see DisjunctionElementAttributeEventFilter
 */
public class DisjunctionElementAttributeEventFilterTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithStartElementAndNoAttributes()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(accept());
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes()));
	}
	
	@Test
	public void acceptWithStartElementAndAttribute()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(accept());
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithAcceptAccept()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(accept());
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithAcceptReject()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(new StubEventFilter(true, false));
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithRejectAccept()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(new StubEventFilter(false, true));
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithRejectReject()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(reject());
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
}
