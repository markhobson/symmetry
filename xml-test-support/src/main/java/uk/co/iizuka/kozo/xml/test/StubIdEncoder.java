/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml-test-support/src/main/java/uk/co/iizuka/kozo/xml/test/StubIdEncoder.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.xml.test;

import java.util.HashMap;
import java.util.Map;

import uk.co.iizuka.common.codec.EncoderException;
import uk.co.iizuka.kozo.xml.IdEncoder;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubIdEncoder.java 95604 2011-11-28 13:02:25Z mark@IIZUKA.CO.UK $
 */
public class StubIdEncoder implements IdEncoder
{
	// fields -----------------------------------------------------------------
	
	private final Map<Object, String> idsByObject;
	
	// constructors -----------------------------------------------------------
	
	public StubIdEncoder()
	{
		idsByObject = new HashMap<Object, String>();
	}
	
	// Encoder methods --------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encode(Object object) throws EncoderException
	{
		return idsByObject.get(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public void setEncodedObject(Object object, String encodedObject)
	{
		idsByObject.put(object, encodedObject);
	}
}
