/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/LeafFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Tests <code>LeafFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: LeafFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
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
