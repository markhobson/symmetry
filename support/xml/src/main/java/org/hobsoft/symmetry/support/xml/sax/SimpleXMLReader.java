/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/SimpleXMLReader.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SimpleXMLReader extends AbstractXMLReader
{
	// XMLReader methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void parse(InputSource input) throws IOException, SAXException
	{
		// no-op
	}
}
