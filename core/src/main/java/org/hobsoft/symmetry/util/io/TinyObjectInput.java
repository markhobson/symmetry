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

import java.io.DataInput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TinyObjectInput extends AbstractObjectInput implements ExplicitObjectInput
{
	// fields -----------------------------------------------------------------
	
	private final ObjectInputStream inputStream;
	
	private Class<? extends Serializable> currentClass;
	
	private Serializable currentObject;
	
	// constructors -----------------------------------------------------------
	
	public TinyObjectInput(DataInput in)
	{
		super(in);
		
		try
		{
			inputStream = new ObjectInputStreamAdapter(this)
			{
				@Override
				public void defaultReadObject() throws IOException, ClassNotFoundException
				{
					TinyObjectInput.this.defaultReadObject();
				}
			};
		}
		catch (IOException exception)
		{
			throw new IllegalStateException("ObjectInputStream() never throws IOException");
		}
	}
	
	// ObjectInput methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readObject() throws ClassNotFoundException, IOException
	{
		// TODO: implement
		throw new UnsupportedOperationException();
	}
	
	// public methods ---------------------------------------------------------
	
	public void defaultReadObject() throws IOException, ClassNotFoundException
	{
		if (currentObject == null)
		{
			throw new NotActiveException("Not in call to readObject");
		}
		
		readFields(currentClass, currentObject);
	}
	
	// ExplicitObjectInput methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readObject(Class<?> klass) throws ClassNotFoundException, IOException
	{
		Object object;
		
		// TODO: support null
		
		if (String.class.isAssignableFrom(klass))
		{
			object = readString();
		}
		else if (klass.isArray())
		{
			object = readArray(klass);
		}
		else if (Enum.class.isAssignableFrom(klass))
		{
			object = readEnum(klass.asSubclass(Enum.class));
		}
		else if (Externalizable.class.isAssignableFrom(klass))
		{
			object = SerializationUtils.readExternal(klass.asSubclass(Externalizable.class), this);
		}
		else if (Serializable.class.isAssignableFrom(klass))
		{
			object = readSerializable(klass.asSubclass(Serializable.class));
		}
		else
		{
			throw new NotSerializableException(klass.getName());
		}
		
		return object;
	}
	
	// private methods --------------------------------------------------------
	
	private String readString() throws IOException
	{
		return readUTF();
	}
	
	private Object readArray(Class<?> klass) throws IOException, ClassNotFoundException
	{
		int length = readInt();
		
		Object array = Array.newInstance(klass.getComponentType(), length);
		
		for (int i = 0; i < length; i++)
		{
			readArrayElement(array, i);
		}
		
		return array;
	}
	
	private void readArrayElement(Object array, int i) throws IOException, ClassNotFoundException
	{
		Class<?> type = array.getClass().getComponentType();
		
		if (type == boolean.class)
		{
			Array.setBoolean(array, i, readBoolean());
		}
		else if (type == byte.class)
		{
			Array.setByte(array, i, readByte());
		}
		else if (type == char.class)
		{
			Array.setChar(array, i, readChar());
		}
		else if (type == double.class)
		{
			Array.setDouble(array, i, readDouble());
		}
		else if (type == float.class)
		{
			Array.setFloat(array, i, readFloat());
		}
		else if (type == int.class)
		{
			Array.setInt(array, i, readInt());
		}
		else if (type == long.class)
		{
			Array.setLong(array, i, readLong());
		}
		else if (type == short.class)
		{
			Array.setShort(array, i, readShort());
		}
		else
		{
			Array.set(array, i, readObject(type));
		}
	}
	
	private <T extends Enum<T>> T readEnum(Class<T> klass) throws IOException
	{
		int ordinal = readUnsignedByte();
		
		T[] constants = klass.getEnumConstants();
		
		for (T constant : constants)
		{
			if (constant.ordinal() == ordinal)
			{
				return constant;
			}
		}
		
		throw new IOException("Unknown enum ordinal: " + ordinal);
	}
	
	private <T extends Serializable> T readSerializable(Class<T> klass) throws IOException
	{
		T serializable = SerializationUtils.newSerializable(klass);
		
		try
		{
			for (Class<?> superclass : SerializationUtils.getSerializableSuperclasses(klass))
			{
				Class<? extends Serializable> serializableSuperclass = superclass.asSubclass(Serializable.class);
				
				if (SerializationUtils.hasReadObject(serializableSuperclass))
				{
					currentClass = serializableSuperclass;
					currentObject = serializable;
					
					SerializationUtils.readObject(serializableSuperclass, serializable, inputStream);
					
					currentClass = null;
					currentObject = null;
				}
				else
				{
					readFields(superclass, serializable);
				}
			}
		}
		catch (ClassNotFoundException exception)
		{
			throw (InvalidClassException) new InvalidClassException(klass.getName(), "unable to create instance")
				.initCause(exception);
		}
		
		if (SerializationUtils.hasReadResolve(klass))
		{
			serializable = klass.cast(SerializationUtils.readResolve(serializable));
		}
		
		return serializable;
	}
	
	private void readFields(Class<?> klass, Object object) throws IOException, ClassNotFoundException
	{
		for (Field field : TinyObjectUtils.getOrderedDeclaredFields(klass))
		{
			int modifiers = field.getModifiers();
			
			if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers))
			{
				field.setAccessible(true);
				
				try
				{
					readField(field, object);
				}
				catch (IllegalAccessException exception)
				{
					// field is accessible
					throw new InternalError();
				}
			}
		}
	}
	
	private void readField(Field field, Object object) throws IOException, ClassNotFoundException,
		IllegalAccessException
	{
		Class<?> type = field.getType();
		
		if (type == boolean.class)
		{
			field.setBoolean(object, readBoolean());
		}
		else if (type == byte.class)
		{
			field.setByte(object, readByte());
		}
		else if (type == char.class)
		{
			field.setChar(object, readChar());
		}
		else if (type == double.class)
		{
			field.setDouble(object, readDouble());
		}
		else if (type == float.class)
		{
			field.setFloat(object, readFloat());
		}
		else if (type == int.class)
		{
			field.setInt(object, readInt());
		}
		else if (type == long.class)
		{
			field.setLong(object, readLong());
		}
		else if (type == short.class)
		{
			field.setShort(object, readShort());
		}
		else
		{
			field.set(object, readObject(type));
		}
	}
}
