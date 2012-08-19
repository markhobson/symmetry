/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/ShowFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Tests <code>ShowFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: ShowFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see ShowFilter
 */
public class ShowFilterTest extends AbstractNodeFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void element()
	{
		NodeFilter filter = new ShowFilter(NodeFilter.SHOW_ELEMENT);
		
		assertAccept(filter, getDocument().createElement("a"));
	}
	
	@Test
	public void toStringElement()
	{
		NodeFilter filter = new ShowFilter(NodeFilter.SHOW_ELEMENT);

		assertEquals("show[SHOW_ELEMENT]", filter.toString());
	}
	
	// TODO: finish tests
	
	@Test
	public void toStringAll()
	{
		NodeFilter filter = new ShowFilter(NodeFilter.SHOW_ALL);

		assertEquals("show[SHOW_ALL]", filter.toString());
	}
}
