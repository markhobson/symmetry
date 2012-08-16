/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/SerializerObjectOutput.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import java.io.IOException;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SerializerObjectOutput.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SerializerObjectOutput extends DelegatingObjectOutput implements SerializerContext
{
	// fields -----------------------------------------------------------------
	
	private final SerializerFactory serializerFactory;
	
	private final Object serializerContext;
	
	// constructors -----------------------------------------------------------
	
	public SerializerObjectOutput(ObjectOutput out, SerializerFactory serializerFactory)
	{
		this(out, serializerFactory, null);
	}
	
	public SerializerObjectOutput(ObjectOutput out, SerializerFactory serializerFactory, Object serializerContext)
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
