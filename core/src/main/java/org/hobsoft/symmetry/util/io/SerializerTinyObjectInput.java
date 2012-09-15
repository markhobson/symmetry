/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/SerializerTinyObjectInput.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.DataInput;
import java.io.IOException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SerializerTinyObjectInput extends TinyObjectInput implements SerializerContext
{
	// TODO: replace with SerializerObjectInput and TinyObjectInput when classes can be extended at runtime
	
	// fields -----------------------------------------------------------------
	
	private final SerializerFactory serializerFactory;
	
	private Object serializerContext;
	
	// constructors -----------------------------------------------------------
	
	public SerializerTinyObjectInput(DataInput in, SerializerFactory serializerFactory)
	{
		this(in, serializerFactory, null);
	}
	
	public SerializerTinyObjectInput(DataInput in, SerializerFactory serializerFactory, Object serializerContext)
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
	
	public void setSerializerContext(Object serializerContext)
	{
		this.serializerContext = serializerContext;
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
