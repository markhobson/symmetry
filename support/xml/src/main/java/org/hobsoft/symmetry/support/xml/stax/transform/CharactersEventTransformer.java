/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/transform/CharactersEventTransformer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.transform;

import java.io.IOException;
import java.io.StringWriter;

import javanet.staxutils.io.XMLWriterUtils;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.EventTransformer;

/**
 * 
 * 
 * @author Mark Hobson
 */
class CharactersEventTransformer implements EventTransformer
{
	// fields -----------------------------------------------------------------
	
	private final XMLEventFactory eventFactory;
	
	// constructors -----------------------------------------------------------
	
	public CharactersEventTransformer()
	{
		eventFactory = XMLEventFactory.newInstance();
	}

	// EventTransformer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public XMLEvent transform(XMLEvent event)
	{
		// shortcut
		if (event.isCharacters())
		{
			return event;
		}
		
		StringWriter writer = new StringWriter();
		
		try
		{
			// TODO: ideally we'd use XMLEvent.writeAsEncodedUnicode but SJSXP 1.0.2 doesn't implement this properly
//			event.writeAsEncodedUnicode(writer);
			
			XMLWriterUtils.writeEvent(event, writer);
		}
		catch (IOException exception)
		{
			// TODO: how best to handle this?
			writer.write("[error]");
		}
		catch (XMLStreamException exception)
		{
			// TODO: how best to handle this?
			writer.write("[error]");
		}
		
		return eventFactory.createCharacters(writer.toString());
	}
}
