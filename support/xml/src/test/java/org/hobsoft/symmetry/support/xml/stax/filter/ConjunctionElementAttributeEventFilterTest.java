/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/ConjunctionElementAttributeEventFilterTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.accept;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.reject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.stream.EventFilter;

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.junit.Test;

/**
 * Tests {@code ConjunctionElementAttributeEventFilter}.
 * 
 * @author Mark Hobson
 * @version $Id: ConjunctionElementAttributeEventFilterTest.java 88590 2011-05-27 09:55:44Z mark@IIZUKA.CO.UK $
 * @see ConjunctionElementAttributeEventFilter
 */
public class ConjunctionElementAttributeEventFilterTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithStartElementAndNoAttributes()
	{
		EventFilter filter = new ConjunctionElementAttributeEventFilter(accept());
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes()));
	}
	
	@Test
	public void acceptWithStartElementAndAttribute()
	{
		EventFilter filter = new ConjunctionElementAttributeEventFilter(accept());
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsConjunctionWithAcceptAccept()
	{
		EventFilter filter = new ConjunctionElementAttributeEventFilter(accept());
		
		assertTrue(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsConjunctionWithAcceptAndReject()
	{
		EventFilter filter = new ConjunctionElementAttributeEventFilter(new StubEventFilter(true, false));
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsConjunctionWithRejectAndAccept()
	{
		EventFilter filter = new ConjunctionElementAttributeEventFilter(new StubEventFilter(false, true));
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
	
	@Test
	public void acceptWithStartElementAndAttributesIsConjunctionWithRejectAndReject()
	{
		EventFilter filter = new ConjunctionElementAttributeEventFilter(reject());
		
		assertFalse(filter.accept(createStartElementWithAnyNameAndAttributes("a", "b")));
	}
}
