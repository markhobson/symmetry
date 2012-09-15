/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml-test-support/src/main/java/uk/co/iizuka/kozo/xml/test/hydrate/AbstractXmlRenderKitTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.xml.test.hydrate;

import static org.junit.Assert.fail;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.test.hydrate.AbstractRenderKitTest;
import org.hobsoft.symmetry.xml.IdEncoder;
import org.hobsoft.symmetry.xml.test.StubIdEncoder;
import org.junit.Before;

/**
 * Provides support for testing XML-based component render kits.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type that this test tests
 */
public abstract class AbstractXmlRenderKitTest<T> extends AbstractRenderKitTest<T>
{
	// fields -----------------------------------------------------------------
	
	private StubIdEncoder idEncoder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpAbstractXmlRenderKitTest() throws XMLStreamException
	{
		OutputStream out = getDehydrationContext().getOutputStream();
		XMLStreamWriter xmlOut = createXMLStreamWriter(out);
		getDehydrationContext().set(XMLStreamWriter.class, xmlOut);
		
		idEncoder = new StubIdEncoder();
		getDehydrationContext().set(IdEncoder.class, idEncoder);
	}
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrate(T component) throws HydrationException
	{
		super.dehydrate(component);
		
		XMLStreamWriter out = getDehydrationContext().get(XMLStreamWriter.class);
		
		try
		{
			close(out);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected final StubIdEncoder getIdEncoder()
	{
		return idEncoder;
	}
	
	// private methods --------------------------------------------------------
	
	private static XMLStreamWriter createXMLStreamWriter(OutputStream out) throws XMLStreamException
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
		return factory.createXMLStreamWriter(out);
	}
	
	private static void close(XMLStreamWriter out) throws XMLStreamException
	{
		// ensure all start tags are closed
		try
		{
			out.writeEndElement();
			
			fail("Unclosed element: " + out.toString());
		}
		catch (XMLStreamException exception)
		{
			// expected
		}
		
		out.flush();
		out.close();
	}
}
