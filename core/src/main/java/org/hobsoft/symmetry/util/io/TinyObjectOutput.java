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

import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TinyObjectOutput extends AbstractObjectOutput
{
	// fields -----------------------------------------------------------------
	
	private final ObjectOutputStream outputStream;
	
	private Class<? extends Serializable> currentClass;
	
	private Serializable currentObject;
	
	// constructors -----------------------------------------------------------
	
	public TinyObjectOutput(DataOutput out)
	{
		super(out);
		
		try
		{
			outputStream = new ObjectOutputStreamAdapter(this)
			{
				@Override
				public void defaultWriteObject() throws IOException
				{
					TinyObjectOutput.this.defaultWriteObject();
				}
			};
		}
		catch (IOException exception)
		{
			throw new IllegalStateException("ObjectOutputStream() never throws IOException");
		}
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
			// TODO: support null
			throw new UnsupportedOperationException("null");
		}
		
		if (object instanceof String)
		{
			writeString((String) object);
		}
		else if (object.getClass().isArray())
		{
			writeArray(object);
		}
		else if (object instanceof Enum)
		{
			writeEnum((Enum<?>) object);
		}
		else if (object instanceof Externalizable)
		{
			SerializationUtils.writeExternal((Externalizable) object, this);
		}
		else if (object instanceof Serializable)
		{
			writeSerializable((Serializable) object);
		}
		else
		{
			throw new NotSerializableException(object.getClass().getName());
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public void defaultWriteObject() throws IOException
	{
		if (currentObject == null)
		{
			throw new NotActiveException("Not in call to writeObject");
		}
		
		writeFields(currentClass, currentObject);
	}
	
	// private methods --------------------------------------------------------
	
	private void writeString(String string) throws IOException
	{
		writeUTF(string);
	}
	
	private void writeArray(Object array) throws IOException
	{
		int length = Array.getLength(array);
		
		writeInt(length);
		
		for (int i = 0; i < length; i++)
		{
			writeArrayElement(array, i);
		}
	}
	
	private void writeArrayElement(Object array, int i) throws IOException
	{
		Class<?> type = array.getClass().getComponentType();
		
		if (type == boolean.class)
		{
			writeBoolean(Array.getBoolean(array, i));
		}
		else if (type == byte.class)
		{
			writeByte(Array.getByte(array, i));
		}
		else if (type == char.class)
		{
			writeChar(Array.getChar(array, i));
		}
		else if (type == double.class)
		{
			writeDouble(Array.getDouble(array, i));
		}
		else if (type == float.class)
		{
			writeFloat(Array.getFloat(array, i));
		}
		else if (type == int.class)
		{
			writeInt(Array.getInt(array, i));
		}
		else if (type == long.class)
		{
			writeLong(Array.getLong(array, i));
		}
		else if (type == short.class)
		{
			writeShort(Array.getShort(array, i));
		}
		else
		{
			writeObject(Array.get(array, i));
		}
	}
	
	private void writeEnum(Enum<?> enumeration) throws IOException
	{
		writeByte(enumeration.ordinal());
	}
	
	private void writeSerializable(Serializable object) throws IOException
	{
		if (SerializationUtils.hasWriteReplace(object.getClass()))
		{
			object = (Serializable) SerializationUtils.writeReplace(object);
		}
		
		for (Class<?> superclass : SerializationUtils.getSerializableSuperclasses(object.getClass()))
		{
			Class<? extends Serializable> serializableSuperclass = superclass.asSubclass(Serializable.class);
			
			if (SerializationUtils.hasWriteObject(serializableSuperclass))
			{
				currentClass = serializableSuperclass;
				currentObject = object;
				
				SerializationUtils.writeObject(serializableSuperclass, object, outputStream);
				
				currentClass = null;
				currentObject = null;
			}
			else
			{
				writeFields(superclass, object);
			}
		}
	}
	
	private void writeFields(Class<?> klass, Object object) throws IOException
	{
		for (Field field : TinyObjectUtils.getOrderedDeclaredFields(klass))
		{
			int modifiers = field.getModifiers();
			
			if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers))
			{
				field.setAccessible(true);
				
				try
				{
					writeField(field, object);
				}
				catch (IllegalAccessException exception)
				{
					// field is accessible
					throw new InternalError();
				}
			}
		}
	}
	
	private void writeField(Field field, Object object) throws IOException, IllegalAccessException
	{
		Class<?> type = field.getType();
		
		if (type == boolean.class)
		{
			writeBoolean(field.getBoolean(object));
		}
		else if (type == byte.class)
		{
			writeByte(field.getByte(object));
		}
		else if (type == char.class)
		{
			writeChar(field.getChar(object));
		}
		else if (type == double.class)
		{
			writeDouble(field.getDouble(object));
		}
		else if (type == float.class)
		{
			writeFloat(field.getFloat(object));
		}
		else if (type == int.class)
		{
			writeInt(field.getInt(object));
		}
		else if (type == long.class)
		{
			writeLong(field.getLong(object));
		}
		else if (type == short.class)
		{
			writeShort(field.getShort(object));
		}
		else
		{
			writeObject(field.get(object));
		}
	}
}
