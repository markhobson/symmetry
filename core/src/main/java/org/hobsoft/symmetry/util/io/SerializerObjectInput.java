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
import java.io.ObjectInput;

/**
 * 
 * 
 * @author Mark Hobson
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
