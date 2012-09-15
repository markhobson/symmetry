/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/DisjunctionEventFilterTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.accept;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.reject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests <code>DisjunctionEventFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: DisjunctionEventFilterTest.java 88550 2011-05-26 10:11:55Z mark@IIZUKA.CO.UK $
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
