/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/filter/AbstractDTDXMLFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.sax.filter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

import uk.co.iizuka.common.xml.dtd.DTD;
import uk.co.iizuka.common.xml.dtd.DTDAttribute;
import uk.co.iizuka.common.xml.dtd.DTDElement;
import uk.co.iizuka.common.xml.dtd.DTDProvider;

/**
 * 
 * 
 * @author	Mark Hobson
 * @version	$Id: AbstractDTDXMLFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractDTDXMLFilter extends XMLFilterImpl
{
	// fields -----------------------------------------------------------------
	
	private DTDProvider dtdProvider;
	
	// constructors -----------------------------------------------------------
	
	public AbstractDTDXMLFilter(XMLReader parent, DTDProvider dtdProvider)
	{
		super(parent);
		
		if (dtdProvider == null)
		{
			throw new NullPointerException("dtdProvider");
		}
		
		this.dtdProvider = dtdProvider;
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		DTD dtd = dtdProvider.getDTD();
		
		if (dtd != null)
		{
			DTDElement dtdElement = dtd.getElement(qName);
			
			if (dtdElement != null)
			{
				attributes = qualifyAttributes(attributes, dtdElement);
			}
		}
		
		super.startElement(uri, localName, qName, attributes);
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract String qualifyAttributeValue(DTDAttribute attribute, String value);
	
	// private methods --------------------------------------------------------
	
	private Attributes qualifyAttributes(Attributes attributes, DTDElement element)
	{
		AttributesImpl qualifiedAttributes = new AttributesImpl();
		
		for (DTDAttribute attribute : element)
		{
			String name = attribute.getName();
			String type = attribute.getType().toString();
			String value = attributes.getValue(name);
			
			String qualifiedValue = qualifyAttributeValue(attribute, value);
			
			if (qualifiedValue != null)
			{
				qualifiedAttributes.addAttribute("", name, name, type, qualifiedValue);
			}
		}
		
		return qualifiedAttributes;
	}
}
