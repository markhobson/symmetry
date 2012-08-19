/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/filter/IgnoreWhitespaceXMLFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.sax.filter;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import uk.co.iizuka.common.xml.dtd.DTDProvider;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: IgnoreWhitespaceXMLFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class IgnoreWhitespaceXMLFilter extends AbstractWhitespaceXMLFilter
{
	// constructors -----------------------------------------------------------
	
	public IgnoreWhitespaceXMLFilter(XMLReader parent)
	{
		super(parent);
	}

	public IgnoreWhitespaceXMLFilter(XMLReader parent, DTDProvider dtdProvider)
	{
		super(parent, dtdProvider);
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
			super.processCharacterBuffer();
		}
	}
}
