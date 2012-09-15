/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/AbstractXMLReader.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import java.io.IOException;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractXMLReader implements XMLReader
{
	// fields -----------------------------------------------------------------

	private EntityResolver entityResolver;
	
	private ContentHandler contentHandler;
	
	private DTDHandler dtdHandler;

	private ErrorHandler errorHandler;
	
	private DeclHandler declHandler;
	
	private LexicalHandler lexicalHandler;
	
	// XMLReader methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		throw new SAXNotRecognizedException("Feature: " + name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		throw new SAXNotRecognizedException("Feature: " + name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		if (SAX.Property.DECLARATION_HANDLER.equals(name))
		{
			return declHandler;
		}
		
		if (SAX.Property.LEXICAL_HANDLER.equals(name))
		{
			return lexicalHandler;
		}
		
		throw new SAXNotRecognizedException("Property: " + name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		if (SAX.Property.DECLARATION_HANDLER.equals(name))
		{
			declHandler = (DeclHandler) value;
		}
		else if (SAX.Property.LEXICAL_HANDLER.equals(name))
		{
			lexicalHandler = (LexicalHandler) value;
		}
		else
		{
			throw new SAXNotRecognizedException("Property: " + name);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setEntityResolver(EntityResolver resolver)
	{
		entityResolver = resolver;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EntityResolver getEntityResolver()
	{
		return entityResolver;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setDTDHandler(DTDHandler handler)
	{
		dtdHandler = handler;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public DTDHandler getDTDHandler()
	{
		return dtdHandler;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setContentHandler(ContentHandler handler)
	{
		contentHandler = handler;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ContentHandler getContentHandler()
	{
		return contentHandler;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setErrorHandler(ErrorHandler handler)
	{
		errorHandler = handler;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ErrorHandler getErrorHandler()
	{
		return errorHandler;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void parse(String systemId) throws IOException, SAXException
	{
		parse(new InputSource(systemId));
	}
	
	// protected methods ------------------------------------------------------
	
	protected DeclHandler getDeclHandler()
	{
		return declHandler;
	}
	
	protected LexicalHandler getLexicalHandler()
	{
		return lexicalHandler;
	}
}
