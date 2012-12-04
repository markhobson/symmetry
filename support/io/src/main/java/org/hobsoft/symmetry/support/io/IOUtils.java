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
package org.hobsoft.symmetry.support.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class IOUtils
{
	// TODO: need more efficient method of serialising EnumSet's - using bitmask rather than arrays
	
	// constructors -----------------------------------------------------------
	
	private IOUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T> T readObject(ObjectInput in, Class<T> klass) throws ClassNotFoundException, IOException
	{
		Object object;
		
		if (in instanceof ExplicitObjectInput)
		{
			object = ((ExplicitObjectInput) in).readObject(klass);
		}
		else
		{
			object = in.readObject();
		}
		
		return klass.cast(object);
	}
	
	public static Object readType(DataInput in, Class<?> type) throws IOException
	{
		// TODO: support boxing
		// TODO: move to DataInput somehow? maybe as Serializer's?
		
		Object value;
		
		if (type == boolean.class)
		{
			value = Boolean.valueOf(in.readBoolean());
		}
		else if (type == byte.class)
		{
			value = Byte.valueOf(in.readByte());
		}
		else if (type == char.class)
		{
			value = Character.valueOf(in.readChar());
		}
		else if (type == double.class)
		{
			value = Double.valueOf(in.readDouble());
		}
		else if (type == float.class)
		{
			value = Float.valueOf(in.readFloat());
		}
		else if (type == int.class)
		{
			value = Integer.valueOf(in.readInt());
		}
		else if (type == long.class)
		{
			value = Long.valueOf(in.readLong());
		}
		else if (type == short.class)
		{
			value = Short.valueOf(in.readShort());
		}
		else if (type == String.class)
		{
			value = in.readUTF();
		}
		else
		{
			throw new IllegalArgumentException("Cannot read type: " + type.getName());
		}
		
		return value;
	}
	
	public static Object readType(ObjectInput in, Class<?> type) throws IOException, ClassNotFoundException
	{
		// TODO: move to ObjectInput somehow? maybe as Serializer's?
		
		Object value;
		
		if (type.isPrimitive() || String.class.equals(type))
		{
			value = readType((DataInput) in, type);
		}
		else
		{
			value = readObject(in, type);
		}
		
		return value;
	}
	
	public static void writeType(DataOutput out, Class<?> type, Object value) throws IOException
	{
		// TODO: support boxing
		// TODO: move to DataOutput somehow? maybe as Serializer's?
		
		if (type == boolean.class)
		{
			out.writeBoolean((Boolean) value);
		}
		else if (type == byte.class)
		{
			out.writeByte((Byte) value);
		}
		else if (type == char.class)
		{
			out.writeChar((Character) value);
		}
		else if (type == double.class)
		{
			out.writeDouble((Double) value);
		}
		else if (type == float.class)
		{
			out.writeFloat((Float) value);
		}
		else if (type == int.class)
		{
			out.writeInt((Integer) value);
		}
		else if (type == long.class)
		{
			out.writeLong((Long) value);
		}
		else if (type == short.class)
		{
			out.writeShort((Short) value);
		}
		else if (type == String.class)
		{
			out.writeUTF((String) value);
		}
		else
		{
			throw new IllegalArgumentException("Cannot write type: " + type.getName());
		}
	}
	
	public static void writeType(ObjectOutput out, Class<?> type, Object value) throws IOException
	{
		// TODO: move to ObjectOutput somehow? maybe as Serializer's?
		
		if (type.isPrimitive() || String.class.equals(type))
		{
			writeType((DataOutput) out, type, value);
		}
		else
		{
			out.writeObject(value);
		}
	}
	
	// private methods --------------------------------------------------------
	
//	private static <E extends Enum<E>> Class getElementType(EnumSet<E> set)
//	{
//		if (set.isEmpty())
//			set = EnumSet.complementOf(set);
//		if (set.isEmpty())
//			throw new UnsupportedOperationException("Cannot determine element type of empty enum EnumSet");
//		return set.iterator().next().getClass();
//	}
}
