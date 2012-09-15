/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/main/java/uk/co/iizuka/kozo/xml/XmlUtils.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.xml;

import java.io.Reader;
import java.io.StringReader;

import javanet.staxutils.XMLEventStreamWriter;
import javanet.staxutils.XMLStreamEventWriter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.hobsoft.symmetry.support.xml.stax.EventReaderTransformer;
import org.hobsoft.symmetry.support.xml.stax.EventTransformer;
import org.hobsoft.symmetry.support.xml.stax.EventWriterFilter;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.and;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.depthEq;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.depthGt;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.not;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.or;
import static org.hobsoft.symmetry.support.xml.stax.filter.TypeEventFilters.dtd;
import static org.hobsoft.symmetry.support.xml.stax.filter.TypeEventFilters.processingInstruction;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class XmlUtils
{
	// fields -----------------------------------------------------------------
	
	private static XMLInputFactory factory;
	
	static
	{
		factory = XMLInputFactory.newInstance();
		
		// disable fetching DTDs
		factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);

		// pass through entity references verbatim
		factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
	}

	// constructors -----------------------------------------------------------
	
	private XmlUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static XMLStreamWriter createFilteredWriter(XMLStreamWriter writer, EventFilter filter)
	{
		XMLEventWriter eventWriter = new XMLStreamEventWriter(writer);
		
		eventWriter = new EventWriterFilter(eventWriter, filter);
		
		return new XMLEventStreamWriter(eventWriter);
	}
	
	public static void writeStartElementWithId(XMLStreamWriter out, String localName, String id)
		throws XMLStreamException
	{
		out.writeStartElement(localName);
		out.writeAttribute("id", id);
	}
	
	public static void writeTextElement(XMLStreamWriter out, String localName, String text) throws XMLStreamException
	{
		out.writeStartElement(localName);
		out.writeCharacters(text);
		out.writeEndElement();
	}
	
	public static void writeAttributeIfNotNull(XMLStreamWriter out, String name, Object value) throws XMLStreamException
	{
		writeAttributeIf(out, name, value, value != null);
	}

	public static void writeAttributeIfNotEmpty(XMLStreamWriter out, String name, String value)
		throws XMLStreamException
	{
		writeAttributeIf(out, name, value, isNotEmpty(value));
	}
	
	public static void writeAttributeIfNotEqual(XMLStreamWriter out, String name, String value, String unlessValue)
		throws XMLStreamException
	{
		boolean write = (value != null) && !value.equals(unlessValue);
		writeAttributeIf(out, name, value, write);
	}
	
	public static void writeAttributeIf(XMLStreamWriter out, String name, Object value, boolean write)
		throws XMLStreamException
	{
		if (write)
		{
			writeAttribute(out, name, value);
		}
	}
	
	public static void writeAttribute(XMLStreamWriter out, String name, Object value)
		throws XMLStreamException
	{
		checkNotNull(out, "out");
		checkNotNull(name, "name");
		checkNotNull(value, "value");
		
		out.writeAttribute(name, value.toString());
	}
	
	public static boolean isXmlFragment(String xml)
	{
		// TODO: handle leading and trailing whitespace?
		return !(xml.startsWith("<") && xml.endsWith(">"));
	}
	
	public static boolean matches(String xml, EventFilter filter) throws XMLStreamException
	{
		XMLEventReader reader = factory.createXMLEventReader(new StringReader(xml));
		reader = factory.createFilteredReader(reader, filter);
		
		boolean matches = reader.hasNext();
		
		reader.close();
		
		return matches;
	}

	public static void writeXmlFragment(XMLStreamWriter out, String xml) throws XMLStreamException
	{
		writeXmlFragment(out, xml, null, null);
	}
	
	public static void writeXmlFragment(XMLStreamWriter out, String xml, EventFilter filter,
		EventTransformer transformer) throws XMLStreamException
	{
		XMLEventReader reader = createFragmentXMLEventReader(xml);
		
		if (filter != null)
		{
			reader = factory.createFilteredReader(reader, filter);
		}
		
		if (transformer != null)
		{
			reader = new EventReaderTransformer(reader, transformer);
		}
		
		XMLEventWriter writer = new XMLStreamEventWriter(out);
		writer.add(reader);
	}
	
	public static void writeId(HydrationContext context, Object component) throws HydrationException,
		XMLStreamException
	{
		writeId(context, "id", component);
	}
	
	public static void writeId(HydrationContext context, String attributeName, Object component)
		throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String id = getId(context, component);
		
		writeAttributeIfNotEmpty(out, attributeName, id);
	}
	
	public static String getId(HydrationContext context, Object component) throws HydrationException
	{
		try
		{
			return context.get(IdEncoder.class).encode(component);
		}
		catch (EncoderException exception)
		{
			throw new HydrationException("Cannot get component id: " + component, exception);
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static <T> T checkNotNull(T object, String name)
	{
		if (object == null)
		{
			throw new NullPointerException(name + " cannot be null");
		}
		
		return object;
	}
	
	private static boolean isNotEmpty(String string)
	{
		return (string != null) && (string.length() > 0);
	}
	
	private static XMLEventReader createFragmentXMLEventReader(String xml) throws XMLStreamException
	{
		// filter out start/end document events and top-level processing instructions and DTDs
		EventFilter filter = and(depthGt(0), not(and(depthEq(0), or(processingInstruction(), dtd()))));
		
		// wrap xml in root tag to keep it well-formed and remove in filter
		if (isXmlFragment(xml))
		{
			xml = "<root>" + xml + "</root>";
			filter = and(filter, depthGt(0));
		}
		
		Reader reader = new StringReader(xml);
		
		XMLEventReader eventReader = factory.createXMLEventReader(reader);
		
		return factory.createFilteredReader(eventReader, filter);
	}
}
