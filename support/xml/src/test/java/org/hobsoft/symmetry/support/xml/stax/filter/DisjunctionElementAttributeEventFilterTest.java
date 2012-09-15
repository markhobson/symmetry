/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/DisjunctionElementAttributeEventFilterTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.EventFilter;

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.junit.Test;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.accept;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.reject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code DisjunctionElementAttributeEventFilter}.
 * 
 * @author Mark Hobson
 * @see DisjunctionElementAttributeEventFilter
 */
public class DisjunctionElementAttributeEventFilterTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithStartElementAndNoAttributes()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(accept());
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes()));
	}
	
	@Test
	public void acceptWithStartElementAndAttribute()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(accept());
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithAcceptAccept()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(accept());
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithAcceptReject()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(new StubEventFilter(true, false));
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithRejectAccept()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(new StubEventFilter(false, true));
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsDisjunctionWithRejectReject()
	{
		EventFilter filter = new DisjunctionElementAttributeEventFilter(reject());
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
}
