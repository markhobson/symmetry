/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/DTDDeclHandler.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import org.hobsoft.symmetry.support.xml.dtd.DTD;
import org.hobsoft.symmetry.support.xml.dtd.DTDElement;
import org.hobsoft.symmetry.support.xml.dtd.DTDParseException;
import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DTDDeclHandler.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDDeclHandler implements DeclHandler, DTDProvider
{
	// fields -----------------------------------------------------------------
	
	private final DTD dtd;
	
	// constructors -----------------------------------------------------------
	
	public DTDDeclHandler()
	{
		dtd = new DTD();
	}
	
	// DeclHandler methods ----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public void elementDecl(String name, String model) throws SAXException
	{
		// workaround for bug #4519431 in crimson for jdk1.4
		if (model.startsWith("(#PCDATA") && !model.endsWith("*"))
		{
			model += "*";
		}
		
		try
		{
			dtd.addElement(name, model);
		}
		catch (DTDParseException exception)
		{
			throw new SAXException(exception);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void attributeDecl(String eName, String aName, String type, String mode, String value) throws SAXException
	{
		// workaround for bug in crimson for jdk1.4
		if (!"CDATA".equals(type)
			&& !"ID".equals(type)
			&& !"IDREF".equals(type)
			&& !"IDREFS".equals(type)
			&& !"ENTITY".equals(type)
			&& !"ENTITIES".equals(type)
			&& !"NMTOKEN".equals(type)
			&& !"NMTOKENS".equals(type)
			&& !type.startsWith("("))
		{
			type = "(" + type + ")";
		}
		
		DTDElement element = dtd.getElement(eName);
		if (element == null)
		{
			element = new DTDElement(eName);
			dtd.addElement(element);
		}
		
		try
		{
			element.addAttribute(aName, type, mode, value);
		}
		catch (DTDParseException exception)
		{
			throw new SAXException(exception);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void internalEntityDecl(String name, String value) throws SAXException
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException
	{
		// no-op
	}
	
	// DTDProvider methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public DTD getDTD()
	{
		return dtd;
	}
}
