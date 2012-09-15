/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml-test-support/src/main/java/uk/co/iizuka/kozo/xml/test/AbstractStaxTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.xml.test;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Provides support for testing classes that use StAX.
 * 
 * @author Mark Hobson
 */
public abstract class AbstractStaxTest
{
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
		try
		{
			xmlWriter.writeEndElement();
			
			fail("Unclosed element: " + writer.toString());
		}
		catch (XMLStreamException exception)
		{
			// expected
		}
		
		xmlWriter.flush();
		xmlWriter.close();
		
		assertEquals(expected, writer.toString());
	}
}
