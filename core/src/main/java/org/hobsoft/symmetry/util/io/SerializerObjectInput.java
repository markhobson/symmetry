/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/SerializerObjectInput.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.ObjectInput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SerializerObjectInput.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SerializerObjectInput extends DelegatingObjectInput implements SerializerContext
{
	// fields -----------------------------------------------------------------
	
	private final SerializerFactory serializerFactory;
	
	private final Object serializerContext;
	
	// constructors -----------------------------------------------------------
	
	public SerializerObjectInput(ObjectInput in, SerializerFactory serializerFactory)
	{
		this(in, serializerFactory, null);
	}
	
	public SerializerObjectInput(ObjectInput in, SerializerFactory serializerFactory, Object serializerContext)
	{
		super(in);
		
		this.serializerFactory = serializerFactory;
		this.serializerContext = serializerContext;
	}
	
	// ExplicitObjectInput methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readObject(Class<?> type) throws ClassNotFoundException, IOException
	{
		return readObjectCapture(type);
	}
	
	// SerializerContext methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getSerializerContext()
	{
		return serializerContext;
	}
	
	// public methods ---------------------------------------------------------
	
	public SerializerFactory getSerializerFactory()
	{
		return serializerFactory;
	}
	
	// private methods --------------------------------------------------------
	
	private <T> T readObjectCapture(Class<T> type) throws ClassNotFoundException, IOException
	{
		Serializer<T> serializer = null;
		
		if (serializerFactory != null)
		{
			serializer = serializerFactory.getSerializer(type);
		}
		
		T object;
		
		if (serializer == null)
		{
			object = type.cast(super.readObject(type));
		}
		else
		{
			object = serializer.readObject(this);
		}
		
		return object;
	}
}
