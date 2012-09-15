/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/EventFiltersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.stream.EventFilter;

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.junit.Test;

/**
 * Tests {@code EventFilters}.
 * 
 * @author Mark Hobson
 * @version $Id: EventFiltersTest.java 88632 2011-05-31 15:54:50Z mark@IIZUKA.CO.UK $
 * @see EventFilters
 */
public class EventFiltersTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void accept()
	{
		assertTrue(EventFilters.accept().accept(createEvent()));
	}
	
	@Test
	public void reject()
	{
		assertFalse(EventFilters.reject().accept(createEvent()));
	}
	
	@Test
	public void notWithAccept()
	{
		assertFalse(EventFilters.not(EventFilters.accept()).accept(createEvent()));
	}
	
	@Test
	public void notWithReject()
	{
		assertTrue(EventFilters.not(EventFilters.reject()).accept(createEvent()));
	}
	
	@Test
	public void startElementWithStartElement()
	{
		assertTrue(EventFilters.startElement("a").accept(createStartElement("a")));
	}
	
	@Test
	public void startElementWithStartElementAndDifferentLocalName()
	{
		assertFalse(EventFilters.startElement("a").accept(createStartElement("b")));
	}
	
	@Test
	public void startElementWithStartElementAndDifferentCaseLocalName()
	{
		assertTrue(EventFilters.startElement("a").accept(createStartElement("A")));
	}
	
	@Test
	public void startElementWithStartElementAndWildcardLocalName()
	{
		assertTrue(EventFilters.startElement("*").accept(createStartElement("a")));
	}
	
	@Test
	public void startElementWithNamespacedStartElement()
	{
		assertTrue(EventFilters.startElement("ns1", "a").accept(createNamespacedStartElement("ns1", "a")));
	}
	
	@Test
	public void startElementWithNamespacedStartElementAndWildcardNamespaceUri()
	{
		assertTrue(EventFilters.startElement("*", "a").accept(createNamespacedStartElement("ns1", "a")));
	}
	
	@Test
	public void startElementWithNamespacedStartElementAndWildcardLocalName()
	{
		assertTrue(EventFilters.startElement("ns1", "*").accept(createNamespacedStartElement("ns1", "a")));
	}
	
	@Test
	public void startElementWithEndElement()
	{
		assertFalse(EventFilters.startElement("a").accept(createEndElement("a")));
	}
	
	@Test
	public void endElementWithEndElement()
	{
		assertTrue(EventFilters.endElement("a").accept(createEndElement("a")));
	}
	
	@Test
	public void endElementWithEndElementAndDifferentLocalName()
	{
		assertFalse(EventFilters.endElement("a").accept(createEndElement("b")));
	}
	
	@Test
	public void endElementWithEndElementAndDifferentCaseLocalName()
	{
		assertTrue(EventFilters.endElement("a").accept(createEndElement("A")));
	}
	
	@Test
	public void endElementWithEndElementAndWildcardLocalName()
	{
		assertTrue(EventFilters.endElement("*").accept(createEndElement("a")));
	}
	
	@Test
	public void endElementWithNamespacedEndElement()
	{
		assertTrue(EventFilters.endElement("ns1", "a").accept(createNamespacedEndElement("ns1", "a")));
	}
	
	@Test
	public void endElementWithNamespacedEndElementAndWildcardNamespaceUri()
	{
		assertTrue(EventFilters.endElement("*", "a").accept(createNamespacedEndElement("ns1", "a")));
	}
	
	@Test
	public void endElementWithNamespacedEndElementAndWildcardLocalName()
	{
		assertTrue(EventFilters.endElement("ns1", "*").accept(createNamespacedEndElement("ns1", "a")));
	}
	
	@Test
	public void endElementWithStartElement()
	{
		assertFalse(EventFilters.endElement("a").accept(createStartElement("a")));
	}
	
	@Test
	public void depthEqOneWithStartDocument()
	{
		EventFilter filter = EventFilters.depthEq(1);
		
		assertFalse(filter.accept(createStartDocument()));
	}
	
	@Test
	public void depthEqOneWithStartDocumentElement()
	{
		EventFilter filter = EventFilters.depthEq(1);
		filter.accept(createStartDocument());
		
		assertTrue(filter.accept(createStartElement()));
	}
	
	@Test
	public void depthEqOneWithStartDocumentElementElement()
	{
		EventFilter filter = EventFilters.depthEq(1);
		filter.accept(createStartDocument());
		filter.accept(createStartElement());
		
		assertFalse(filter.accept(createStartElement()));
	}
	
	@Test
	public void depthEqOneWithStartDocumentElementElementEndElement()
	{
		EventFilter filter = EventFilters.depthEq(1);
		filter.accept(createStartDocument());
		filter.accept(createStartElement());
		filter.accept(createStartElement());
		
		assertFalse(filter.accept(createEndElement()));
	}
	
	@Test
	public void depthEqOneWithStartDocumentElementElementEndElementElement()
	{
		EventFilter filter = EventFilters.depthEq(1);
		filter.accept(createStartDocument());
		filter.accept(createStartElement());
		filter.accept(createStartElement());
		filter.accept(createEndElement());
		
		assertTrue(filter.accept(createEndElement()));
	}
	
	@Test
	public void depthEqOneWithStartDocumentElementElementEndElementElementDocument()
	{
		EventFilter filter = EventFilters.depthEq(1);
		filter.accept(createStartDocument());
		filter.accept(createStartElement());
		filter.accept(createStartElement());
		filter.accept(createEndElement());
		filter.accept(createEndElement());
		
		assertFalse(filter.accept(createEndDocument()));
	}
	
	@Test
	public void depthGeZeroWithStartDocument()
	{
		EventFilter filter = EventFilters.depthGe(0);

		assertTrue(filter.accept(createStartDocument()));
	}

	@Test
	public void depthGeZeroWithEndDocument()
	{
		EventFilter filter = EventFilters.depthGe(0);
		
		assertTrue(filter.accept(createStartDocument()));
	}
	
	@Test
	public void depthGeOneWithStartDocument()
	{
		EventFilter filter = EventFilters.depthGe(1);
		
		assertFalse(filter.accept(createStartDocument()));
	}
	
	@Test
	public void depthGeOneWithStartDocumentStartEvent()
	{
		EventFilter filter = EventFilters.depthGe(1);
		filter.accept(createStartDocument());
		
		assertTrue(filter.accept(createEvent()));
	}
	
	@Test
	public void depthGeOneWithStartDocumentStartEventEndDocument()
	{
		EventFilter filter = EventFilters.depthGe(1);
		filter.accept(createStartDocument());
		filter.accept(createEvent());
		
		assertFalse(filter.accept(createEndDocument()));
	}
	
	@Test
	public void depthGeTwoWithStartDocument()
	{
		EventFilter filter = EventFilters.depthGe(2);
		
		assertFalse(filter.accept(createStartDocument()));
	}
	
	@Test
	public void depthGeTwoWithStartDocumentStartElement()
	{
		EventFilter filter = EventFilters.depthGe(2);
		filter.accept(createStartDocument());
		
		assertFalse(filter.accept(createStartElement()));
	}
	
	@Test
	public void depthGeTwoWithStartDocumentStartElementEvent()
	{
		EventFilter filter = EventFilters.depthGe(2);
		filter.accept(createStartDocument());
		filter.accept(createStartElement());
		
		assertTrue(filter.accept(createEvent()));
	}
	
	@Test
	public void depthGeTwoWithStartDocumentStartElementEventEndElement()
	{
		EventFilter filter = EventFilters.depthGe(2);
		filter.accept(createStartDocument());
		filter.accept(createStartElement());
		filter.accept(createEvent());
		
		assertFalse(filter.accept(createEndElement()));
	}
	
	@Test
	public void depthGeTwoWithStartDocumentStartElementEventEndElementEndDocument()
	{
		EventFilter filter = EventFilters.depthGe(2);
		filter.accept(createStartDocument());
		filter.accept(createStartElement());
		filter.accept(createEvent());
		filter.accept(createEndElement());
		
		assertFalse(filter.accept(createEndDocument()));
	}
}
