/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/ConjunctionEventFilterTest.java $
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
 * Tests <code>ConjunctionEventFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: ConjunctionEventFilterTest.java 88550 2011-05-26 10:11:55Z mark@IIZUKA.CO.UK $
 * @see ConjunctionEventFilter
 */
public class ConjunctionEventFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithEmpty()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter();
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAccept()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithReject()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(reject());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptAccept()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(accept(), accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptReject()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(accept(), reject());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectAccept()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(reject(), accept());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectReject()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(reject(), reject());
		
		assertFalse(filter.accept(null));
	}
}
