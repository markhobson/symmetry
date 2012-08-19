/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/TypeEventFilterTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax.filter;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javanet.staxutils.events.EntityDeclarationEvent;
import javanet.staxutils.events.NotationDeclarationEvent;

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.NotationDeclaration;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests <code>TypeEventFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: TypeEventFilterTest.java 88550 2011-05-26 10:11:55Z mark@IIZUKA.CO.UK $
 * @see TypeEventFilter
 */
public class TypeEventFilterTest
{
	// TODO: finish tests
	
	// fields -----------------------------------------------------------------
	
	private StartElement startElement;
	
	private EndElement endElement;
	
	private ProcessingInstruction processingInstruction;
	
	private Characters characters;
	
	private Comment comment;
	
	private Characters space;
	
	private StartDocument startDocument;
	
	private EndDocument endDocument;
	
	private EntityReference entityReference;
	
	private Attribute attribute;
	
	private DTD dtd;
	
	private Characters cdata;
	
	private Namespace namespace;
	
	private NotationDeclaration notationDeclaration;
	
	private EntityDeclaration entityDeclaration;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		XMLEventFactory factory = XMLEventFactory.newInstance();
		
		startElement = factory.createStartElement(new QName("a"), null, null);
		endElement = factory.createEndElement(new QName("a"), null);
		processingInstruction = factory.createProcessingInstruction(null, null);
		characters = factory.createCharacters(null);
		comment = factory.createComment(null);
		space = factory.createSpace(null);
		startDocument = factory.createStartDocument();
		endDocument = factory.createEndDocument();
		entityReference = factory.createEntityReference(null, null);
		attribute = factory.createAttribute(new QName("a"), null);
		dtd = factory.createDTD(null);
		cdata = factory.createCData(null);
		namespace = factory.createNamespace(null);
		
		// use stax-utils events since no factory method exists
		notationDeclaration = new NotationDeclarationEvent(null, null, null);
		entityDeclaration = new EntityDeclarationEvent(null, null, null);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void startElement()
	{
		EventFilter filter = new TypeEventFilter(START_ELEMENT);
		
		assertTrue(filter.accept(startElement));
		assertFalse(filter.accept(endElement));
		assertFalse(filter.accept(processingInstruction));
		assertFalse(filter.accept(characters));
		assertFalse(filter.accept(comment));
		assertFalse(filter.accept(space));
		assertFalse(filter.accept(startDocument));
		assertFalse(filter.accept(endDocument));
		assertFalse(filter.accept(entityReference));
		assertFalse(filter.accept(attribute));
		assertFalse(filter.accept(dtd));
		assertFalse(filter.accept(cdata));
		assertFalse(filter.accept(namespace));
		assertFalse(filter.accept(notationDeclaration));
		assertFalse(filter.accept(entityDeclaration));
	}
	
	@Test
	public void endElement()
	{
		EventFilter filter = new TypeEventFilter(END_ELEMENT);
		
		assertFalse(filter.accept(startElement));
		assertTrue(filter.accept(endElement));
		assertFalse(filter.accept(processingInstruction));
		assertFalse(filter.accept(characters));
		assertFalse(filter.accept(comment));
		assertFalse(filter.accept(space));
		assertFalse(filter.accept(startDocument));
		assertFalse(filter.accept(endDocument));
		assertFalse(filter.accept(entityReference));
		assertFalse(filter.accept(attribute));
		assertFalse(filter.accept(dtd));
		assertFalse(filter.accept(cdata));
		assertFalse(filter.accept(namespace));
		assertFalse(filter.accept(notationDeclaration));
		assertFalse(filter.accept(entityDeclaration));
	}
	
	@Test
	public void element()
	{
		EventFilter filter = new TypeEventFilter(START_ELEMENT, END_ELEMENT);
		
		assertTrue(filter.accept(startElement));
		assertTrue(filter.accept(endElement));
		assertFalse(filter.accept(processingInstruction));
		assertFalse(filter.accept(characters));
		assertFalse(filter.accept(comment));
		assertFalse(filter.accept(space));
		assertFalse(filter.accept(startDocument));
		assertFalse(filter.accept(endDocument));
		assertFalse(filter.accept(entityReference));
		assertFalse(filter.accept(attribute));
		assertFalse(filter.accept(dtd));
		assertFalse(filter.accept(cdata));
		assertFalse(filter.accept(namespace));
		assertFalse(filter.accept(notationDeclaration));
		assertFalse(filter.accept(entityDeclaration));
	}
}
