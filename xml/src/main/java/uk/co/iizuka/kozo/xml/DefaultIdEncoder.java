/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/main/java/uk/co/iizuka/kozo/xml/DefaultIdEncoder.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.xml;

import java.util.HashMap;
import java.util.Map;

import uk.co.iizuka.common.codec.EncoderException;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultIdEncoder.java 95597 2011-11-28 12:44:19Z mark@IIZUKA.CO.UK $
 */
class DefaultIdEncoder implements IdEncoder
{
	// fields -----------------------------------------------------------------
	
	private final Map<Object, String> idsByObject;
	
	private int nextId;
	
	// constructors -----------------------------------------------------------
	
	public DefaultIdEncoder()
	{
		idsByObject = new HashMap<Object, String>();
		nextId = 0;
	}

	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encode(Object object) throws EncoderException
	{
		String id;
		
		if (idsByObject.containsKey(object))
		{
			id = idsByObject.get(object);
		}
		else
		{
			id = nextId();
			idsByObject.put(object, id);
		}
		
		return id;
	}
	
	// private methods --------------------------------------------------------
	
	private String nextId()
	{
		return String.valueOf(nextId++);
	}
}
