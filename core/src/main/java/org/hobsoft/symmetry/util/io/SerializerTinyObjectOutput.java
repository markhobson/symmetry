/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/SerializerTinyObjectOutput.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.DataOutput;
import java.io.IOException;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SerializerTinyObjectOutput.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SerializerTinyObjectOutput extends TinyObjectOutput implements SerializerContext
{
	// TODO: replace with SerializerObjectOutput and TinyObjectOutput when classes can be extended at runtime
	
	// fields -----------------------------------------------------------------
	
	private final SerializerFactory serializerFactory;
	
	private Object serializerContext;
	
	// constructors -----------------------------------------------------------
	
	public SerializerTinyObjectOutput(DataOutput out, SerializerFactory serializerFactory)
	{
		this(out, serializerFactory, null);
	}
	
	public SerializerTinyObjectOutput(DataOutput out, SerializerFactory serializerFactory, Object serializerContext)
	{
		super(out);
		
		this.serializerFactory = serializerFactory;
		this.serializerContext = serializerContext;
	}
	
	// ObjectOutput methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeObject(Object object) throws IOException
	{
		if (object == null)
		{
			super.writeObject(object);
		}
		else
		{
			writeObjectCapture(object, object.getClass());
		}
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
	
	private <T> void writeObjectCapture(Object object, Class<T> type) throws IOException
	{
		writeObject(type.cast(object), type);
	}
	
	private <T> void writeObject(T object, Class<T> type) throws IOException
	{
		Serializer<T> serializer = null;
		
		if (serializerFactory != null)
		{
			serializer = serializerFactory.getSerializer(type);
		}
		
		if (serializer == null)
		{
			super.writeObject(object);
		}
		else
		{
			serializer.writeObject(this, object);
		}
	}
}
