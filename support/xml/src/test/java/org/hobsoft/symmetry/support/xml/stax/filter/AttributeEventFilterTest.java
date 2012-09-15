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

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests {@code AttributeEventFilter}.
 * 
 * @author Mark Hobson
 * @see AttributeEventFilter
 */
public class AttributeEventFilterTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithAttribute()
	{
		AttributeEventFilter filter = new AttributeEventFilter("a");
		
		assertTrue(filter.accept(createAttribute("a")));
	}
	
	@Test
	public void acceptWithNamespacedAttribute()
	{
		AttributeEventFilter filter = new AttributeEventFilter("u", "a");
		
		assertTrue(filter.accept(createNamespacedAttribute("u", "a")));
	}
	
	@Test
	public void acceptWithPrefixedAttribute()
	{
		AttributeEventFilter filter = new AttributeEventFilter("u", "a", "p");
		
		assertTrue(filter.accept(createNamespacedAttribute("u", "a", "p")));
	}

	@Test
	public void acceptWithAttributeAndLocalNameWildcard()
	{
		AttributeEventFilter filter = new AttributeEventFilter("*");
		
		assertTrue(filter.accept(createAttribute()));
	}
	
	@Test
	public void acceptWithAttributeAndNamespaceUriWildcard()
	{
		AttributeEventFilter filter = new AttributeEventFilter("*", "a");
		
		assertTrue(filter.accept(createNamespacedAttribute("a")));
	}
}
