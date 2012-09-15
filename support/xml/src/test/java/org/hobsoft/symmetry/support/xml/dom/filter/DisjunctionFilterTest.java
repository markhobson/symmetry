/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/DisjunctionFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests <code>DisjunctionFilter</code>.
 * 
 * @author Mark Hobson
 * @see DisjunctionFilter
 */
public class DisjunctionFilterTest extends AbstractNodeFilterTest
{
	// TODO: test chaining multiple filters together
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptAccept()
	{
		assertAccept(new DisjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void acceptReject()
	{
		assertAccept(new DisjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void acceptSkip()
	{
		assertAccept(new DisjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void acceptNull()
	{
		assertAccept(new DisjunctionFilter(ConstantFilter.ACCEPT, null), getDocument());
	}
	
	@Test
	public void rejectAccept()
	{
		assertAccept(new DisjunctionFilter(ConstantFilter.REJECT, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void rejectReject()
	{
		assertReject(new DisjunctionFilter(ConstantFilter.REJECT, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void rejectSkip()
	{
		assertSkip(new DisjunctionFilter(ConstantFilter.REJECT, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void rejectNull()
	{
		assertReject(new DisjunctionFilter(ConstantFilter.REJECT, null), getDocument());
	}
	
	@Test
	public void skipAccept()
	{
		assertAccept(new DisjunctionFilter(ConstantFilter.SKIP, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void skipReject()
	{
		assertSkip(new DisjunctionFilter(ConstantFilter.SKIP, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void skipSkip()
	{
		assertSkip(new DisjunctionFilter(ConstantFilter.SKIP, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void skipNull()
	{
		assertSkip(new DisjunctionFilter(ConstantFilter.SKIP, null), getDocument());
	}
	
	@Test
	public void nullAccept()
	{
		assertAccept(new DisjunctionFilter(null, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void nullReject()
	{
		assertReject(new DisjunctionFilter(null, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void nullSkip()
	{
		assertSkip(new DisjunctionFilter(null, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void nullNull()
	{
		assertSkip(new DisjunctionFilter(null, null), getDocument());
	}
	
	@Test
	public void noFilters()
	{
		assertSkip(new DisjunctionFilter(), getDocument());
	}
	
	@Test
	public void toStringNoArgs()
	{
		assertEquals("()", new DisjunctionFilter().toString());
	}
	
	@Test
	public void toStringSingleArgs()
	{
		assertEquals("(mock)", new DisjunctionFilter(new MockFilter()).toString());
	}
	
	@Test
	public void toStringMultipleArgs()
	{
		assertEquals("(mock or mock)", new DisjunctionFilter(new MockFilter(), new MockFilter()).toString());
	}
}
