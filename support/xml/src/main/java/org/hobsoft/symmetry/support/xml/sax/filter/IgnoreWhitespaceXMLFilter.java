/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/filter/IgnoreWhitespaceXMLFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax.filter;

import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * 
 * 
 * @author Mark Hobson
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
