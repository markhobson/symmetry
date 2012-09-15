/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/ConstantFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Tests <code>ConstantFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: ConstantFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see ConstantFilter
 */
public class ConstantFilterTest extends AbstractNodeFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void accept()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_ACCEPT);
		assertAccept(filter, getDocument());
	}
	
	@Test
	public void reject()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_REJECT);
		assertReject(filter, getDocument());
	}
	
	@Test
	public void skip()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_SKIP);
		assertSkip(filter, getDocument());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidResult()
	{
		try
		{
			new ConstantFilter((short) 123);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals(exception.getMessage(), "result must be FILTER_ACCEPT, FILTER_REJECT or FILTER_SKIP");
			
			throw exception;
		}
	}
	
	@Test
	public void toStringWithAccept()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_ACCEPT);
		assertEquals("accept", filter.toString());
	}
	
	@Test
	public void toStringWithReject()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_REJECT);
		assertEquals("reject", filter.toString());
	}
	
	@Test
	public void toStringWithSkip()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_SKIP);
		assertEquals("skip", filter.toString());
	}
}
