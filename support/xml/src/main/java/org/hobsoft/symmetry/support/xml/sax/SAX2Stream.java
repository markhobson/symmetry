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
package org.hobsoft.symmetry.support.xml.sax;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.hobsoft.symmetry.support.xml.XML;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * 
 * 
 * @author Mark Hobson
 * @author Mike Atkin
 */
public class SAX2Stream extends AbstractSAXHandler
{
	// TODO: use Appendable internally rather than Writer
	
	// constants --------------------------------------------------------------
	
	private static final String PROLOGUE_CHARSET = "UTF-8";
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	private static final String NEWLINE = "\r\n";
	
	// fields -----------------------------------------------------------------
	
	private OutputStream out;
	
	private Writer writer;
	
	private boolean omitXMLDeclaration;
	
	private boolean omitDocType;
	
	private Charset prologueCharset;
	
	private Charset charset;
	
	private boolean inStartElement;
	
	// constructors -----------------------------------------------------------
	
	public SAX2Stream(OutputStream out)
	{
		this();
		
		this.out = out;
		writer = new OutputStreamWriter(out, prologueCharset);
	}
	
	public SAX2Stream(Writer writer)
	{
		this();
		
		this.writer = writer;
	}
	
	private SAX2Stream()
	{
		omitXMLDeclaration = false;
		omitDocType = false;
		
		prologueCharset = Charset.forName(PROLOGUE_CHARSET);
		charset = Charset.forName(DEFAULT_CHARSET);
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

		if (!omitXMLDeclaration)
		{
			write("<?xml version=\"1.0\" encoding=\"");
			write(charset.name());
			write("\"?>");
			writeNewline();
		}

		// change charset if possible
		if (!prologueCharset.equals(charset))
		{
			changeCharset(charset);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException
	{
		super.endDocument();
		
		flushStartElement();
		
		// TODO: should we restore prologue charset.  Can this object be reused?
		try
		{
			writer.flush();
		}
		catch (IOException exception)
		{
			throw new SAXException(exception);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, qName, attributes);
		
		flushStartElement();
		
		// start element
		write('<');
		write(qName);

		// write any namespaces
		for (String prefix : getPrefixMap().keySet())
		{
			String prefixURI = getPrefixMap().get(prefix);
			
			write(' ');
			write("xmlns");
			if (prefix.length() > 0)
			{
				write(':');
				write(prefix);
			}
			write("=\"");
			write(prefixURI);
			write('\"');
		}
		getPrefixMap().clear();
		
		// write any attributes
		for (int i = 0; i < attributes.getLength(); i++)
		{
			write(' ');
			write(attributes.getQName(i));
			write("=\"");
			escape(attributes.getValue(i));
			write('\"');
		}
		
		// keep tag open for self-closing tags
		inStartElement = true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		super.endElement(uri, localName, qName);

		if (inStartElement)
		{
			write("/>");
			inStartElement = false;
		}
		else
		{
			write("</");
			write(qName);
			write('>');
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		super.characters(ch, start, length);

		flushStartElement();
		
		if (inCDATA())
		{
			write(ch, start, length);
		}
		else
		{
			escape(ch, start, length);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
	{
		super.ignorableWhitespace(ch, start, length);

		flushStartElement();
		
		escape(ch, 0, ch.length);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException
	{
		super.processingInstruction(target, data);

		flushStartElement();
		
		// TODO: implement
	}

	// LexicalHandler methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException
	{
		super.startDTD(name, publicId, systemId);
		
		if (!omitDocType)
		{
			write("<!DOCTYPE ");
			write(name);
			
			if (publicId != null)
			{
				write(" PUBLIC \"");
				write(publicId);
				write("\" \"");
				write(systemId);
				write('\"');
			}
			else if (systemId != null)
			{
				write(" SYSTEM \"");
				write(systemId);
				write('\"');
			}
			
			write(">");
			writeNewline();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startCDATA() throws SAXException
	{
		super.startCDATA();

		flushStartElement();
		
		write("<![CDATA[");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endCDATA() throws SAXException
	{
		super.endCDATA();
		
		write("]]>");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void comment(char[] ch, int start, int length) throws SAXException
	{
		super.comment(ch, start, length);

		if (!inDTD())
		{
			write("<!--");
			write(ch, start, length);
			write("-->");
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public boolean isOmitXMLDeclaration()
	{
		return omitXMLDeclaration;
	}
	
	public void setOmitXMLDeclaration(boolean omitXMLDeclaration)
	{
		this.omitXMLDeclaration = omitXMLDeclaration;
	}
	
	public boolean isOmitDocType()
	{
		return omitDocType;
	}
	
	public void setOmitDocType(boolean omitDocType)
	{
		this.omitDocType = omitDocType;
	}
	
	public Charset getEncodingCharset()
	{
		return charset;
	}
	
	public void setEncodingCharset(String charsetName)
	{
		setEncodingCharset(Charset.forName(charsetName));
	}

	public void setEncodingCharset(Charset charset)
	{
		this.charset = charset;
	}
	
	// private methods --------------------------------------------------------
	
	private void changeCharset(Charset newCharset) throws SAXException
	{
		// can only change charset for OutputStreams
		if (out != null)
		{
			try
			{
				writer.flush();
				writer = new OutputStreamWriter(out, newCharset);
			}
			catch (IOException exception)
			{
				throw new SAXException(exception);
			}
		}
	}
	
	private void flushStartElement() throws SAXException
	{
		if (inStartElement)
		{
			write('>');
			inStartElement = false;
		}
	}

	private void escape(String string) throws SAXException
	{
		if (string == null)
		{
			return;
		}
		
		escape(string.toCharArray());
	}
	
	private void escape(char[] chars) throws SAXException
	{
		escape(chars, 0, chars.length);
	}
	
	private void escape(char[] chars, int offset, int length) throws SAXException
	{
		if (length == 0)
		{
			return;
		}
		
		int start = offset;
		for (int i = offset; i < offset + length; i++)
		{
			char c = chars[i];
			
			if (!XML.isValidChar(c))
			{
				throw new SAXException("Invalid XML character: 0x" + Integer.toHexString(c));
			}
			
			if (c == '<' || c == '>' || c == '&' || c == '"')
			{
				write(chars, start, i - start);
				
				switch (c)
				{
					case '<':
						write("&lt;");
						break;
						
					case '>':
						write("&gt;");
						break;
						
					case '&':
						write("&amp;");
						break;
						
					case '"':
						write("&quot;");
						break;
					
					default:
						break;
				}
				
				start = i + 1;
			}
		}
		
		write(chars, start, offset + length - start);
	}
	
	private void writeNewline() throws SAXException
	{
		write(NEWLINE);
	}
	
	private void write(char c) throws SAXException
	{
		try
		{
			writer.write(c);
		}
		catch (IOException exception)
		{
			throw new SAXException(exception);
		}
	}
	
	private void write(char[] chars, int offset, int length) throws SAXException
	{
		try
		{
			writer.write(chars, offset, length);
		}
		catch (IOException exception)
		{
			throw new SAXException(exception);
		}
	}
	
	private void write(String string) throws SAXException
	{
		try
		{
			writer.write(string);
		}
		catch (IOException exception)
		{
			throw new SAXException(exception);
		}
	}
}
