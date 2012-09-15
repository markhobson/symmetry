/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/NegationFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests <code>NegationFilter</code>.
 * 
 * @author Mark Hobson
 * @see NegationFilter
 */
public class NegationFilterTest extends AbstractNodeFilterTest
{
	// TODO: resolve ternary vs boolean logic

	// tests ------------------------------------------------------------------
	
	@Test
	public void accept()
	{
		assertSkip(new NegationFilter(ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void reject()
	{
		assertReject(new NegationFilter(ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void skip()
	{
		assertAccept(new NegationFilter(ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void toStringWithNoArgs()
	{
		assertEquals("not mock", new NegationFilter(new MockFilter()).toString());
	}
}
