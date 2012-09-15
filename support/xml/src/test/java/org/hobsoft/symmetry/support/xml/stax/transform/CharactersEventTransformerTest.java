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

import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.singletonMap;

/**
 * Tests {@code CharactersEventTransformer}.
 * 
 * @author Mark Hobson
 * @see CharactersEventTransformer
 */
public class CharactersEventTransformerTest extends AbstractXMLEventTest
{
	// fields -----------------------------------------------------------------
	
	private CharactersEventTransformer transformer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		transformer = new CharactersEventTransformer();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void transformWithStartElement()
	{
		XMLEvent actual = transformer.transform(createStartElement("a"));
		
		assertCharactersEquals("<a>", actual);
	}
	
	@Test
	public void transformWithStartElementAndAttribute()
	{
		XMLEvent actual = transformer.transform(createStartElementWithAttributes("a", singletonMap("b", "c")));
		
		assertCharactersEquals("<a b='c'>", actual);
	}

	@Test
	public void transformWithStartElementAndDefaultPrefixedAttribute()
	{
		Map<QName, String> attributes = singletonMap(new QName("u", "b", ""), "c");
		StartElement event = createStartElementWithNamespacedAttributes("a", attributes);
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<a b='c'>", actual);
	}
	
	@Test
	public void transformWithStartElementAndPrefixedAttribute()
	{
		Map<QName, String> attributes = singletonMap(new QName("u", "b", "p"), "c");
		StartElement event = createStartElementWithNamespacedAttributes("a", attributes);
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<a p:b='c'>", actual);
	}
	
	@Test
	public void transformWithDefaultPrefixedStartElement()
	{
		XMLEvent actual = transformer.transform(createNamespacedStartElement("u", "a", ""));
		
		assertCharactersEquals("<a>", actual);
	}
	
	@Test
	public void transformWithDefaultPrefixedStartElementAndAttribute()
	{
		StartElement event = createNamespacedStartElementWithAttributes("u", "a", "", singletonMap("b", "c"));
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<a b='c'>", actual);
	}

	@Test
	public void transformWithDefaultPrefixedStartElementAndDefaultPrefixedAttribute()
	{
		Map<QName, String> attributes = singletonMap(new QName("u", "b", ""), "c");
		StartElement event = createNamespacedStartElementWithNamespacedAttributes("u", "a", "", attributes);
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<a b='c'>", actual);
	}
	
	@Test
	public void transformWithDefaultPrefixedStartElementAndPrefixedAttribute()
	{
		Map<QName, String> attributes = singletonMap(new QName("u", "b", "p"), "c");
		StartElement event = createNamespacedStartElementWithNamespacedAttributes("u", "a", "", attributes);
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<a p:b='c'>", actual);
	}

	@Test
	public void transformWithPrefixedStartElement()
	{
		XMLEvent actual = transformer.transform(createNamespacedStartElement("u", "a", "p"));
		
		assertCharactersEquals("<p:a>", actual);
	}

	@Test
	public void transformWithPrefixedStartElementAndAttribute()
	{
		StartElement event = createNamespacedStartElementWithAttributes("u", "a", "p", singletonMap("b", "c"));
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<p:a b='c'>", actual);
	}

	@Test
	public void transformWithPrefixedStartElementAndDefaultPrefixedAttribute()
	{
		Map<QName, String> attributes = singletonMap(new QName("u", "b", ""), "c");
		StartElement event = createNamespacedStartElementWithNamespacedAttributes("u", "a", "p", attributes);
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<p:a b='c'>", actual);
	}
	
	@Test
	public void transformWithPrefixedStartElementAndPrefixedAttribute()
	{
		Map<QName, String> attributes = singletonMap(new QName("u", "b", "p"), "c");
		StartElement event = createNamespacedStartElementWithNamespacedAttributes("u", "a", "p", attributes);
		XMLEvent actual = transformer.transform(event);
		
		assertCharactersEquals("<p:a p:b='c'>", actual);
	}
	
	// TODO: test namespace attributes

	@Test
	public void transformWithEndElement()
	{
		XMLEvent actual = transformer.transform(createEndElement("a"));
		
		assertCharactersEquals("</a>", actual);
	}
	
	@Test
	public void transformWithDefaultPrefixedEndElement()
	{
		XMLEvent actual = transformer.transform(createNamespacedEndElement("u", "a", ""));
		
		assertCharactersEquals("</a>", actual);
	}
	
	@Test
	public void transformWithPrefixedEndElement()
	{
		XMLEvent actual = transformer.transform(createNamespacedEndElement("u", "a", "p"));
		
		assertCharactersEquals("</p:a>", actual);
	}
	
	// TODO: processing instruction
	
	@Test
	public void transformWithCharacters()
	{
		XMLEvent actual = transformer.transform(createCharacters("a"));
		
		assertCharactersEquals("a", actual);
	}
	
	@Test
	public void transformWithComment()
	{
		XMLEvent actual = transformer.transform(createComment("a"));
		
		assertCharactersEquals("<!--a-->", actual);
	}
	
	// TODO: comment
	
	@Test
	public void transformWithSpace()
	{
		XMLEvent actual = transformer.transform(createSpace(" "));
		
		assertCharactersEquals(" ", actual);
	}
	
	// TODO: start document
	
	// TODO: end document
	
	// TODO: entity reference
	
	// TODO: attribute
	
	// TODO: DTD
	
	// TODO: CDATA
	
	// TODO: namespace
	
	// TODO: notation
	
	// TODO: entity declaration
}