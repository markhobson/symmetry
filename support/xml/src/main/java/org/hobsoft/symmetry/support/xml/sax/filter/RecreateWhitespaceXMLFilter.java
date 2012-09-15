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
package org.hobsoft.symmetry.support.xml.sax.filter;

import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class RecreateWhitespaceXMLFilter extends AbstractWhitespaceXMLFilter
{
	// constants --------------------------------------------------------------
	
	private static final char[] NEWLINE = "\r\n".toCharArray();
	
	private static final char[] DEFAULT_INDENTATION = "   ".toCharArray();
	
	// fields -----------------------------------------------------------------

	private char[] indentation;
	
	private boolean inStartElement;

	private boolean newline;
	
	// constructors -----------------------------------------------------------
	
	public RecreateWhitespaceXMLFilter(XMLReader parent)
	{
		this(parent, null);
	}

	public RecreateWhitespaceXMLFilter(XMLReader parent, DTDProvider dtdProvider)
	{
		super(parent, dtdProvider);
		
		indentation = DEFAULT_INDENTATION;
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();

		inStartElement = false;
		newline = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException
	{
		flushCharacterBuffer();
		flushNewLine();
		
		super.endDocument();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		flushCharacterBuffer();
		flushStartElement();
		flushNewLine(0);

		super.startElement(uri, localName, qName, attributes);

		inStartElement = true;
		newline = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		flushCharacterBuffer();
		flushNewLine(-1);

		super.endElement(uri, localName, qName);

		inStartElement = false;
		newline = true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException
	{
		super.processingInstruction(target, data);

		writeNewLine();
	}
	
	// AbstractWhitespaceXMLFilter methods ------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void processCharacterBuffer() throws SAXException
	{
		if (!isBufferWhitespace() || isWhitespaceSignificant())
		{
			flushStartElement();
			
			super.processCharacterBuffer();
		
			newline = true;
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public String getIndentation()
	{
		return new String(indentation);
	}
	
	public void setIndentation(String indentation)
	{
		this.indentation = indentation.toCharArray();
	}
	
	// private methods --------------------------------------------------------
	
	private void flushStartElement() throws SAXException
	{
		if (inStartElement)
		{
			writeNewLine();
			
			inStartElement = false;
		}
	}

	private void flushNewLine() throws SAXException
	{
		flushNewLine(-1);
	}

	private void flushNewLine(int delta) throws SAXException
	{
		if (newline)
		{
			writeNewLine(delta);
			
			newline = false;
		}
	}

	private void writeNewLine() throws SAXException
	{
		writeNewLine(0);
	}

	private void writeNewLine(int delta) throws SAXException
	{
		if (!isWhitespaceSignificant())
		{
			ignorableWhitespaceBypass(NEWLINE, 0, NEWLINE.length);
		
			int depth = getElementDepth() + delta;
			
			for (int i = 0; i < depth; i++)
			{
				ignorableWhitespaceBypass(indentation, 0, indentation.length);
			}
		}
	}
}
