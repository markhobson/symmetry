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
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeFilter;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>LeafFilter</code>.
 * 
 * @author Mark Hobson
 * @see LeafFilter
 */
public class LeafFilterTest extends AbstractNodeFilterTest
{
	// fields -----------------------------------------------------------------
	
	private NodeFilter filter;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		filter = new LeafFilter();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void elementWithNoChildren()
	{
		assertAccept(filter, getDocument().createElement("a"));
	}
	
	@Test
	public void elementWithChildren()
	{
		Element element = getDocument().createElement("a");
		element.appendChild(getDocument().createTextNode(""));
		
		assertSkip(filter, element);
	}
	
	@Test
	public void toStringWithNoArgs()
	{
		assertEquals("leaf", filter.toString());
	}
}
