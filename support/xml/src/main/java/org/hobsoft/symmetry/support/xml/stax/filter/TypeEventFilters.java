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
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.EventFilter;

import static javax.xml.stream.XMLStreamConstants.ATTRIBUTE;
import static javax.xml.stream.XMLStreamConstants.CDATA;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.COMMENT;
import static javax.xml.stream.XMLStreamConstants.DTD;
import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.ENTITY_DECLARATION;
import static javax.xml.stream.XMLStreamConstants.ENTITY_REFERENCE;
import static javax.xml.stream.XMLStreamConstants.NAMESPACE;
import static javax.xml.stream.XMLStreamConstants.NOTATION_DECLARATION;
import static javax.xml.stream.XMLStreamConstants.PROCESSING_INSTRUCTION;
import static javax.xml.stream.XMLStreamConstants.SPACE;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.type;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class TypeEventFilters
{
	// constants --------------------------------------------------------------
	
	private static final EventFilter START_ELEMENT_FILTER = type(START_ELEMENT);
	
	private static final EventFilter END_ELEMENT_FILTER = type(END_ELEMENT);
	
	private static final EventFilter PROCESSING_INSTRUCTION_FILTER = type(PROCESSING_INSTRUCTION);
	
	private static final EventFilter CHARACTERS_FILTER = type(CHARACTERS);
	
	private static final EventFilter COMMENT_FILTER = type(COMMENT);
	
	private static final EventFilter SPACE_FILTER = type(SPACE);
	
	private static final EventFilter START_DOCUMENT_FILTER = type(START_DOCUMENT);
	
	private static final EventFilter END_DOCUMENT_FILTER = type(END_DOCUMENT);
	
	private static final EventFilter ENTITY_REFERENCE_FILTER = type(ENTITY_REFERENCE);
	
	private static final EventFilter ATTRIBUTE_FILTER = type(ATTRIBUTE);
	
	private static final EventFilter DTD_FILTER = type(DTD);
	
	private static final EventFilter CDATA_FILTER = type(CDATA);
	
	private static final EventFilter NAMESPACE_FILTER = type(NAMESPACE);
	
	private static final EventFilter NOTATION_DECLARATION_FILTER = type(NOTATION_DECLARATION);
	
	private static final EventFilter ENTITY_DECLARATION_FILTER = type(ENTITY_DECLARATION);
	
	private static final EventFilter ELEMENT_FILTER = type(START_ELEMENT, END_ELEMENT);
	
	private static final EventFilter DOCUMENT_FILTER = type(START_DOCUMENT, END_DOCUMENT);
	
	// constructors -----------------------------------------------------------
	
	private TypeEventFilters()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static EventFilter startElement()
	{
		return START_ELEMENT_FILTER;
	}
	
	public static EventFilter endElement()
	{
		return END_ELEMENT_FILTER;
	}
	
	public static EventFilter processingInstruction()
	{
		return PROCESSING_INSTRUCTION_FILTER;
	}
	
	public static EventFilter characters()
	{
		return CHARACTERS_FILTER;
	}
	
	public static EventFilter comment()
	{
		return COMMENT_FILTER;
	}
	
	public static EventFilter space()
	{
		return SPACE_FILTER;
	}
	
	public static EventFilter startDocument()
	{
		return START_DOCUMENT_FILTER;
	}
	
	public static EventFilter endDocument()
	{
		return END_DOCUMENT_FILTER;
	}
	
	public static EventFilter entityReference()
	{
		return ENTITY_REFERENCE_FILTER;
	}
	
	public static EventFilter attribute()
	{
		return ATTRIBUTE_FILTER;
	}
	
	public static EventFilter dtd()
	{
		return DTD_FILTER;
	}
	
	public static EventFilter cdata()
	{
		return CDATA_FILTER;
	}
	
	public static EventFilter namespace()
	{
		return NAMESPACE_FILTER;
	}
	
	public static EventFilter notationDeclaration()
	{
		return NOTATION_DECLARATION_FILTER;
	}
	
	public static EventFilter entityDeclaration()
	{
		return ENTITY_DECLARATION_FILTER;
	}
	
	public static EventFilter element()
	{
		return ELEMENT_FILTER;
	}
	
	public static EventFilter document()
	{
		return DOCUMENT_FILTER;
	}
}
