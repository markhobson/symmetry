/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/DisjunctionEventFilterTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import org.junit.Test;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.accept;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.reject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests <code>DisjunctionEventFilter</code>.
 * 
 * @author Mark Hobson
 * @see DisjunctionEventFilter
 */
public class DisjunctionEventFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithEmpty()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter();
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAccept()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithReject()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(reject());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptAccept()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(accept(), accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptReject()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(accept(), reject());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectAccept()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(reject(), accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectReject()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(reject(), reject());
		
		assertFalse(filter.accept(null));
	}
}
