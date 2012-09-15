/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/ConjunctionFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests <code>ConjunctionFilter</code>.
 * 
 * @author Mark Hobson
 * @see ConjunctionFilter
 */
public class ConjunctionFilterTest extends AbstractNodeFilterTest
{
	// TODO: test chaining multiple filters together
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptAccept()
	{
		assertAccept(new ConjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void acceptReject()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void acceptSkip()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void acceptNull()
	{
		assertAccept(new ConjunctionFilter(ConstantFilter.ACCEPT, null), getDocument());
	}
	
	@Test
	public void rejectAccept()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void rejectReject()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void rejectSkip()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void rejectNull()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, null), getDocument());
	}
	
	@Test
	public void skipAccept()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.SKIP, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void skipReject()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.SKIP, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void skipSkip()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.SKIP, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void skipNull()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.SKIP, null), getDocument());
	}
	
	@Test
	public void nullAccept()
	{
		assertAccept(new ConjunctionFilter(null, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void nullReject()
	{
		assertReject(new ConjunctionFilter(null, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void nullSkip()
	{
		assertSkip(new ConjunctionFilter(null, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void nullNull()
	{
		assertSkip(new ConjunctionFilter(null, null), getDocument());
	}
	
	@Test
	public void noFilters()
	{
		assertSkip(new ConjunctionFilter(), getDocument());
	}
	
	@Test
	public void toStringNoArgs()
	{
		assertEquals("()", new ConjunctionFilter().toString());
	}
	
	@Test
	public void toStringSingleArgs()
	{
		assertEquals("(mock)", new ConjunctionFilter(new MockFilter()).toString());
	}
	
	@Test
	public void toStringMultipleArgs()
	{
		assertEquals("(mock and mock)", new ConjunctionFilter(new MockFilter(), new MockFilter()).toString());
	}
}
