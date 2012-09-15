/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/ElementFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.traversal.NodeFilter;

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
