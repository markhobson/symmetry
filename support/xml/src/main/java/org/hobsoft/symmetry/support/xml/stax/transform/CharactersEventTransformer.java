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
