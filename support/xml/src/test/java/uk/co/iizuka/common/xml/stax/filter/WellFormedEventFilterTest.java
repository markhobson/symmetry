/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/WellFormedEventFilterTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.stream.EventFilter;

import org.junit.Test;

import uk.co.iizuka.common.xml.stax.AbstractXMLEventTest;

/**
 * Tests {@code WellFormedEventFilter}.
 * 
 * @author Mark Hobson
 * @version $Id: WellFormedEventFilterTest.java 88594 2011-05-27 10:31:46Z mark@IIZUKA.CO.UK $
 * @see WellFormedEventFilter
 */
public class WellFormedEventFilterTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithAcceptedStartDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		
		assertTrue(filter.accept(createStartDocument()));
	}
	
	@Test
	public void acceptWithAcceptedStartDocumentAcceptsEndDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		filter.accept(createStartDocument());
		
		assertTrue(filter.accept(createEndDocument()));
	}

	@Test
	public void acceptWithRejectedStartDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		
		assertFalse(filter.accept(createStartDocument()));
	}
	
	@Test
	public void acceptWithRejectedStartDocumentRejectsEndDocument()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		filter.accept(createStartDocument());
		
		assertFalse(filter.accept(createEndDocument()));
	}
	
	@Test
	public void acceptWithAcceptedStartElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		
		assertTrue(filter.accept(createStartElement()));
	}
	
	@Test
	public void acceptWithAcceptedStartElementAcceptsEndElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(true));
		filter.accept(createStartElement());
		
		assertTrue(filter.accept(createEndElement()));
	}

	@Test
	public void acceptWithRejectedStartElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		
		assertFalse(filter.accept(createStartElement()));
	}
	
	@Test
	public void acceptWithRejectedStartElementRejectsEndElement()
	{
		EventFilter filter = new WellFormedEventFilter(new StubEventFilter(false));
		filter.accept(createStartElement());
		
		assertFalse(filter.accept(createEndElement()));
	}
}
