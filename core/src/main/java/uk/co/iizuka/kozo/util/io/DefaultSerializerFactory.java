/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/DefaultSerializerFactory.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultSerializerFactory.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class DefaultSerializerFactory implements SerializerFactory
{
	// fields -----------------------------------------------------------------
	
	private final Map<Class<?>, Serializer<?>> serializersByType;
	
	// constructors -----------------------------------------------------------
	
	public DefaultSerializerFactory()
	{
		serializersByType = new HashMap<Class<?>, Serializer<?>>();
	}
	
	// SerializerFactory methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> Serializer<T> getSerializer(Class<T> type)
	{
		return (Serializer<T>) serializersByType.get(type);
	}
	
	// public methods ---------------------------------------------------------
	
	public <T> void setSerializer(Class<T> type, Serializer<T> serializer)
	{
		serializersByType.put(type, serializer);
	}
}
