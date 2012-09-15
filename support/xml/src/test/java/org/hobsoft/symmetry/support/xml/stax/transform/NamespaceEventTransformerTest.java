/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/transform/NamespaceEventTransformerTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.transform;

import javanet.staxutils.helpers.EventMatcher;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests {@code NamespaceEventTransformer}.
 * 
 * @author Mark Hobson
 * @see NamespaceEventTransformer
 */
public class NamespaceEventTransformerTest
{
	// fields -----------------------------------------------------------------
	
	private XMLEventFactory eventFactory;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		eventFactory = XMLEventFactory.newInstance();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void transformWithStartElement()
	{
		XMLEvent event = eventFactory.createStartElement("", null, "a", null, null);
		
		StartElement expected = eventFactory.createStartElement("", "uri", "a", null, null);

		StartElement actual = new NamespaceEventTransformer("uri").transform(event).asStartElement();
		assertEventEquals(expected, actual);
	}
	
	@Test
	public void transformWithEndElement()
	{
		XMLEvent event = eventFactory.createEndElement("", null, "a", null);
		
		EndElement expected = eventFactory.createEndElement("", "uri", "a", null);

		EndElement actual = new NamespaceEventTransformer("uri").transform(event).asEndElement();
		assertEventEquals(expected, actual);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertEventEquals(StartElement expected, StartElement actual)
	{
		assertTrue(EventMatcher.eventsMatch(expected, actual));
		// TODO: assert namespace context?
	}
	
	private static void assertEventEquals(EndElement expected, EndElement actual)
	{
		assertTrue(EventMatcher.eventsMatch(expected, actual));
		assertTrue(EventMatcher.matchNamespaces(expected.getNamespaces(), actual.getNamespaces()));
	}
}
