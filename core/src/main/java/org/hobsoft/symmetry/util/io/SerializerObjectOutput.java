/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
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
