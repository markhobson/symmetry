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

import org.junit.Test;
import org.w3c.dom.traversal.NodeFilter;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>ElementFilter</code>.
 * 
 * @author Mark Hobson
 * @see ElementFilter
 */
public class ElementFilterTest extends AbstractNodeFilterTest
{
	// tests ------------------------------------------------------------------

	@Test
	public void allElementsAllNSWithElement()
	{
		NodeFilter filter = new ElementFilter("*", "*");
		assertAccept(filter, getDocument().createElement("a"));
	}

	@Test
	public void allElementsAllNSWithElementNS()
	{
		NodeFilter filter = new ElementFilter("*", "*");
		assertAccept(filter, getDocument().createElementNS("ns", "a"));
	}

	@Test
	public void allElementsAllNSWithElementNSPrefix()
	{
		NodeFilter filter = new ElementFilter("*", "*");
		assertAccept(filter, getDocument().createElementNS("ns", "p:a"));
	}

	@Test
	public void allElementsNSWithElement()
	{
		NodeFilter filter = new ElementFilter("ns", "*");
		assertSkip(filter, getDocument().createElement("a"));
	}

	@Test
	public void allElementsNSWithElementNS()
	{
		NodeFilter filter = new ElementFilter("ns", "*");
		assertAccept(filter, getDocument().createElementNS("ns", "a"));
	}

	@Test
	public void allElementsNSWithElementNSPrefix()
	{
		NodeFilter filter = new ElementFilter("ns", "*");
		assertAccept(filter, getDocument().createElementNS("ns", "p:a"));
	}

	@Test
	public void allElementsNSWithElementNSDifferentNamespace()
	{
		NodeFilter filter = new ElementFilter("ns", "*");
		assertSkip(filter, getDocument().createElementNS("ns2", "a"));
	}

	@Test
	public void elementWithElement()
	{
		NodeFilter filter = new ElementFilter(null, "a");
		assertAccept(filter, getDocument().createElement("a"));
	}

	@Test
	public void elementWithElementNS()
	{
		NodeFilter filter = new ElementFilter(null, "a");
		assertSkip(filter, getDocument().createElementNS("ns", "a"));
	}
	
	// TODO: finish elementWithElement* tests

	@Test
	public void elementNSWithElement()
	{
		NodeFilter filter = new ElementFilter("ns", "a");
		assertSkip(filter, getDocument().createElement("a"));
	}

	@Test
	public void elementNSWithElementNS()
	{
		NodeFilter filter = new ElementFilter("ns", "a");
		assertAccept(filter, getDocument().createElementNS("ns", "a"));
	}

	@Test
	public void elementNSWithElementNSPrefix()
	{
		NodeFilter filter = new ElementFilter("ns", "a");
		assertAccept(filter, getDocument().createElementNS("ns", "p:a"));
	}

	@Test
	public void elementNSWithElementNSDifferentElement()
	{
		NodeFilter filter = new ElementFilter("ns", "a");
		assertSkip(filter, getDocument().createElementNS("ns", "b"));
	}

	@Test
	public void elementNSWithElementNSDifferentNamespace()
	{
		NodeFilter filter = new ElementFilter("ns", "a");
		assertSkip(filter, getDocument().createElementNS("ns2", "a"));
	}

	@Test
	public void multipleElementsNSWithElements()
	{
		NodeFilter filter = new ElementFilter("ns", "a", "b", "c");
		assertSkip(filter, getDocument().createElement("a"));
		assertSkip(filter, getDocument().createElement("b"));
		assertSkip(filter, getDocument().createElement("c"));
	}

	@Test
	public void multipleElementsNSWithElementsNS()
	{
		NodeFilter filter = new ElementFilter("ns", "a", "b", "c");
		assertAccept(filter, getDocument().createElementNS("ns", "a"));
		assertAccept(filter, getDocument().createElementNS("ns", "b"));
		assertAccept(filter, getDocument().createElementNS("ns", "c"));
	}

	@Test
	public void multipleElementsNSWithElementsNSPrefix()
	{
		NodeFilter filter = new ElementFilter("ns", "a", "b", "c");
		assertAccept(filter, getDocument().createElementNS("ns", "p:a"));
		assertAccept(filter, getDocument().createElementNS("ns", "p:b"));
		assertAccept(filter, getDocument().createElementNS("ns", "p:c"));
	}

	@Test
	public void multipleElementsNSWithElementNSDifferentElement()
	{
		NodeFilter filter = new ElementFilter("ns", "a", "b", "c");
		assertSkip(filter, getDocument().createElementNS("ns", "d"));
	}

	@Test
	public void multipleElementsNSWithElementNSDifferentNamespace()
	{
		NodeFilter filter = new ElementFilter("ns", "a", "b", "c");
		assertSkip(filter, getDocument().createElementNS("ns2", "a"));
	}
	
	@Test
	public void toStringWithNoArgs()
	{
		assertEquals("element[uri=*,name=*]", new ElementFilter().toString());
	}
	
	@Test
	public void toStringWithNamespaceURI()
	{
		assertEquals("element[uri=ns,name=*]", new ElementFilter("ns").toString());
	}
	
	@Test
	public void toStringWithLocalName()
	{
		assertEquals("element[uri=*,name=a]", new ElementFilter("*", "a").toString());
	}
	
	@Test
	public void toStringWithLocalNames()
	{
		assertEquals("element[uri=*,names=[a,b,c]]", new ElementFilter("*", "a", "b", "c").toString());
	}
}
