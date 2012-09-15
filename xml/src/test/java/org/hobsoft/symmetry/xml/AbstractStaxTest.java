/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/test/java/uk/co/iizuka/kozo/xml/AbstractStaxTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.xml;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.junit.Before;

/**
 * Provides support for testing classes that use StAX.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractStaxTest.java 95597 2011-11-28 12:44:19Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractStaxTest
{
	// TODO: avoid duplication with kozo-xml-test's AbstractStaxTest
	
	// fields -----------------------------------------------------------------
	
	private StringWriter writer;
	
	private XMLStreamWriter xmlWriter;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpStaxTest() throws XMLStreamException
	{
		writer = new StringWriter();
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		xmlWriter = factory.createXMLStreamWriter(writer);
	}
	
	// protected methods ------------------------------------------------------
	
	protected final XMLStreamWriter getXMLStreamWriter()
	{
		return xmlWriter;
	}
	
	protected final void assertXml(String expected) throws XMLStreamException
	{
		// ensure all start tags are closed
		xmlWriter.writeEndDocument();
		
		xmlWriter.flush();
		xmlWriter.close();
		
		assertEquals(expected, writer.toString());
	}
}
