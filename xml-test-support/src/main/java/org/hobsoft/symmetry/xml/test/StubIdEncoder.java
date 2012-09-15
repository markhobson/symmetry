/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml-test-support/src/main/java/uk/co/iizuka/kozo/xml/test/StubIdEncoder.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.xml.test;

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.support.codec.EncoderException;
import org.hobsoft.symmetry.xml.IdEncoder;

/**
 * 
 * 
 * @author Mark Hobson
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
