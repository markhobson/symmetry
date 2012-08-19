/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/DTDParser.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.sax;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DTDParser.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDParser
{
	// constants --------------------------------------------------------------
	
	private static final String PUBLIC_ID = "-//IIZUKA//DTDParser";
	
	private static final String DOCTYPE_XML = "<!DOCTYPE root PUBLIC '" + PUBLIC_ID + "' 'systemId'><root/>";
	
	private static final DefaultHandler DEFAULT_DEFAULT_HANDLER = new DefaultHandler();

	// classes ----------------------------------------------------------------
	
	private static class SimpleEntityResolver implements EntityResolver
	{
		private EntityResolver parent;
		private String publicId;
		private InputSource in;
		
		public SimpleEntityResolver(EntityResolver parent, String publicId, InputStream in)
		{
			this(parent, publicId, new InputSource(in));
		}
		
		public SimpleEntityResolver(EntityResolver parent, String publicId, String uri)
		{
			this(parent, publicId, new InputSource(uri));
		}
		
		public SimpleEntityResolver(EntityResolver parent, String publicId, File file) throws FileNotFoundException
		{
			this(parent, publicId, new FileInputStream(file));
		}
		
		public SimpleEntityResolver(EntityResolver parent, String publicId, InputSource in)
		{
			this.parent = parent;
			this.publicId = publicId;
			this.in = in;
		}
		
		public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException
		{
			if (this.publicId.equals(publicId))
			{
				return in;
			}
			
			return parent.resolveEntity(publicId, systemId);
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private final XMLReader reader;
	
	// constructors -----------------------------------------------------------
	
	public DTDParser() throws ParserConfigurationException, SAXException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		reader = parser.getXMLReader();
	}
	
	// public methods ---------------------------------------------------------
	
	public void parse(InputStream in, DeclHandler declHandler) throws SAXException, IOException
	{
		parse(in, DEFAULT_DEFAULT_HANDLER, declHandler);
	}
	
	public void parse(InputStream in, EntityResolver entityResolver, DeclHandler declHandler) throws SAXException,
		IOException
	{
		parse(new SimpleEntityResolver(entityResolver, PUBLIC_ID, in), declHandler);
	}
	
	public void parse(String uri, DeclHandler declHandler) throws SAXException, IOException
	{
		parse(uri, DEFAULT_DEFAULT_HANDLER, declHandler);
	}
	
	public void parse(String uri, EntityResolver entityResolver, DeclHandler declHandler) throws SAXException,
		IOException
	{
		parse(new SimpleEntityResolver(entityResolver, PUBLIC_ID, uri), declHandler);
	}
	
	public void parse(File file, DeclHandler declHandler) throws SAXException, IOException
	{
		parse(file, DEFAULT_DEFAULT_HANDLER, declHandler);
	}
	
	public void parse(File file, EntityResolver entityResolver, DeclHandler declHandler) throws SAXException,
		IOException
	{
		parse(new SimpleEntityResolver(entityResolver, PUBLIC_ID, file), declHandler);
	}
	
	public void parse(InputSource in, DeclHandler declHandler) throws SAXException, IOException
	{
		parse(in, DEFAULT_DEFAULT_HANDLER, declHandler);
	}
	
	public void parse(InputSource in, EntityResolver entityResolver, DeclHandler declHandler) throws SAXException,
		IOException
	{
		parse(new SimpleEntityResolver(entityResolver, PUBLIC_ID, in), declHandler);
	}
	
	// private methods --------------------------------------------------------
	
	private void parse(EntityResolver entityResolver, DeclHandler declHandler) throws SAXException, IOException
	{
		reader.setEntityResolver(entityResolver);
		reader.setProperty(SAX.Property.DECLARATION_HANDLER, declHandler);
		
		reader.parse(new InputSource(new ByteArrayInputStream(DOCTYPE_XML.getBytes())));
	}
}
