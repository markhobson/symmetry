/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.xml.test.hydrate;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.test.hydrate.AbstractRenderKitTest;
import org.hobsoft.symmetry.xml.IdEncoder;
import org.hobsoft.symmetry.xml.test.StubIdEncoder;
import org.junit.Before;

import static org.junit.Assert.fail;

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
