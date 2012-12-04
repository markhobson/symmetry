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

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class SerializationUtils
{
	// TODO: support readObjectNoData
	
	// constructors -----------------------------------------------------------
	
	private SerializationUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Serializable> T newSerializable(Class<T> klass) throws InvalidClassException
	{
		return newInstance(klass, getSerializableConstructor(klass));
	}
	
	public static <T extends Externalizable> void writeExternal(T object, ObjectOutput out) throws IOException
	{
		Externalizable replace = object;
		
		Class<? extends Externalizable> klass = object.getClass();
		
		if (hasWriteReplace(klass))
		{
			replace = klass.cast(writeReplace(klass));
		}
		
		replace.writeExternal(out);
	}
	
	public static <T extends Externalizable> T readExternal(Class<T> klass, ObjectInput in) throws IOException,
		ClassNotFoundException
	{
		T object = newExternalizable(klass);
		
		object.readExternal(in);
		
		if (hasReadResolve(klass))
		{
			object = klass.cast(readResolve(object));
		}
		
		return object;
	}
	
	public static <T extends Serializable> List<Class<? super T>> getSerializableSuperclasses(Class<T> serializable)
	{
		List<Class<? super T>> superclasses = new ArrayList<Class<? super T>>();
		
		Class<? super T> klass = serializable;
		
		while (Serializable.class.isAssignableFrom(klass))
		{
			superclasses.add(klass);
			
			klass = klass.getSuperclass();
		}
		
		Collections.reverse(superclasses);
		
		return superclasses;
	}
	
	public static boolean hasWriteObject(Class<? extends Serializable> klass)
	{
		return (getWriteObjectMethod(klass) != null);
	}
	
	public static boolean hasReadObject(Class<? extends Serializable> klass)
	{
		return (getReadObjectMethod(klass) != null);
	}
	
	public static boolean hasWriteReplace(Class<?> klass)
	{
		return (getWriteReplaceMethod(klass) != null);
	}
	
	public static boolean hasReadResolve(Class<?> klass)
	{
		return (getReadResolveMethod(klass) != null);
	}
	
	public static void writeObject(Class<? extends Serializable> klass, Serializable object, ObjectOutputStream out)
		throws IOException
	{
		Method method = getWriteObjectMethod(klass);
		
		if (method == null)
		{
			throw new IllegalArgumentException("Serializable does not define a writeObject method: " + object);
		}
		
		try
		{
			invokeMethod(method, object, out);
		}
		catch (InvocationTargetException exception)
		{
			throwIOException(exception.getTargetException());
		}
	}
	
	public static void readObject(Class<? extends Serializable> klass, Serializable object, ObjectInputStream in)
		throws IOException, ClassNotFoundException
	{
		Method method = getReadObjectMethod(klass);
		
		if (method == null)
		{
			throw new IllegalArgumentException("Serializable does not define a readObject method: " + object);
		}
		
		try
		{
			invokeMethod(method, object, in);
		}
		catch (InvocationTargetException exception)
		{
			Throwable cause = exception.getTargetException();
			
			if (cause instanceof ClassNotFoundException)
			{
				throw (ClassNotFoundException) cause;
			}
			
			throwIOException(cause);
		}
	}
	
	public static Object writeReplace(Object object) throws ObjectStreamException
	{
		Method method = getWriteReplaceMethod(object.getClass());
		
		if (method == null)
		{
			throw new IllegalArgumentException("Serializable does not define a writeReplace method: " + object);
		}
		
		try
		{
			return invokeMethod(method, object);
		}
		catch (InvocationTargetException exception)
		{
			Throwable cause = exception.getTargetException();
			
			throwIfUnchecked(cause);
			
			if (cause instanceof ObjectStreamException)
			{
				throw (ObjectStreamException) cause;
			}
			
			// TODO: throw proper exception
			throw new InvalidClassException("");
		}
	}
	
	public static Object readResolve(Object object) throws ObjectStreamException
	{
		Method method = getReadResolveMethod(object.getClass());
		
		if (method == null)
		{
			throw new IllegalArgumentException("Serializable does not define a readResolve method: " + object);
		}
		
		try
		{
			return invokeMethod(method, object);
		}
		catch (InvocationTargetException exception)
		{
			Throwable cause = exception.getTargetException();
			
			throwIfUnchecked(cause);
			
			if (cause instanceof ObjectStreamException)
			{
				throw (ObjectStreamException) cause;
			}
			
			// TODO: throw proper exception
			throw new InvalidClassException("");
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends Externalizable> T newExternalizable(Class<T> klass) throws InvalidClassException
	{
		return newInstance(klass, getExternalizableConstructor(klass));
	}
	
	private static <T extends Serializable> Constructor<T> getSerializableConstructor(Class<T> klass)
	{
		Class<? super T> nonSerialClass = klass;
		
		while (Serializable.class.isAssignableFrom(nonSerialClass))
		{
			nonSerialClass = nonSerialClass.getSuperclass();
			
			if (nonSerialClass == null)
			{
				return null;
			}
		}
		
		Constructor<? super T> superConstructor;
		
		try
		{
			superConstructor = nonSerialClass.getDeclaredConstructor();
		}
		catch (NoSuchMethodException exception)
		{
			return null;
		}
		
		int modifiers = superConstructor.getModifiers();
		
		// constructor cannot be private
		if (Modifier.isPrivate(modifiers))
		{
			return null;
		}
		
		// constructor can only be package-protected if within same package
		if (isPackageProtected(modifiers) && !packageEquals(nonSerialClass, klass))
		{
			return null;
		}
		
		return newConstructorForSerialization(klass, superConstructor);
	}
	
	private static <T extends Externalizable> Constructor<T> getExternalizableConstructor(Class<T> klass)
	{
		Constructor<T> constructor;
		
		try
		{
			constructor = klass.getDeclaredConstructor();
		}
		catch (NoSuchMethodException exception)
		{
			return null;
		}
		
		// constructor must be public
		if (!Modifier.isPublic(constructor.getModifiers()))
		{
			return null;
		}
		
		return constructor;
	}
	
	private static Method getWriteObjectMethod(Class<? extends Serializable> klass)
	{
		return getPrivateMethod(klass, void.class, "writeObject", ObjectOutputStream.class);
	}
	
	private static Method getReadObjectMethod(Class<? extends Serializable> klass)
	{
		return getPrivateMethod(klass, void.class, "readObject", ObjectInputStream.class);
	}
	
	private static Method getWriteReplaceMethod(Class<?> klass)
	{
		return getMethod(klass, Object.class, "writeReplace");
	}
	
	private static Method getReadResolveMethod(Class<?> klass)
	{
		return getMethod(klass, Object.class, "readResolve");
	}
	
	private static Method getPrivateMethod(Class<?> klass, Class<?> returnType, String name, Class<?>... paramTypes)
	{
		Method method = getMethod(klass, returnType, name, paramTypes);
		
		if (method != null && !Modifier.isPrivate(method.getModifiers()))
		{
			return null;
		}
		
		return method;
	}
	
	private static Method getMethod(Class<?> klass, Class<?> returnType, String name, Class<?>... paramTypes)
	{
		Method method;
		
		try
		{
			method = klass.getDeclaredMethod(name, paramTypes);
		}
		catch (NoSuchMethodException exception)
		{
			return null;
		}
		
		if (!returnType.equals(method.getReturnType()))
		{
			return null;
		}
		
		if (Modifier.isStatic(method.getModifiers()))
		{
			return null;
		}
		
		return method;
	}
	
	private static Object invokeMethod(Method method, Object object, Object... params) throws InvocationTargetException
	{
		method.setAccessible(true);
		
		try
		{
			return method.invoke(object, params);
		}
		catch (IllegalAccessException exception)
		{
			// method is accessible
			throw new InternalError();
		}
	}
	
	private static void throwIOException(Throwable exception) throws IOException
	{
		throwIfUnchecked(exception);
		
		if (exception instanceof IOException)
		{
			throw (IOException) exception;
		}
		
		throw (IOException) new IOException("Unexpected exception type").initCause(exception);
	}
	
	private static void throwIfUnchecked(Throwable exception)
	{
		if (exception instanceof RuntimeException)
		{
			throw (RuntimeException) exception;
		}
		
		if (exception instanceof Error)
		{
			throw (Error) exception;
		}
	}
	
	private static <T> Constructor<T> newConstructorForSerialization(Class<T> classToInstantiate,
		Constructor<? super T> constructorToCall)
	{
		// TODO: can we achieve this without using sun.reflect.ReflectionFactory?
		
		try
		{
			Class<?> factoryClass = Class.forName("sun.reflect.ReflectionFactory");
			
			Object factory = factoryClass.getMethod("getReflectionFactory").invoke(null);
			
			@SuppressWarnings("unchecked")
			Constructor<T> constructor = (Constructor<T>) factoryClass
				.getMethod("newConstructorForSerialization", Class.class, Constructor.class)
				.invoke(factory, classToInstantiate, constructorToCall);
			
			return constructor;
		}
		catch (ClassNotFoundException exception)
		{
			throw new RuntimeException(exception);
		}
		catch (NoSuchMethodException exception)
		{
			throw new RuntimeException(exception);
		}
		catch (IllegalAccessException exception)
		{
			throw new RuntimeException(exception);
		}
		catch (InvocationTargetException exception)
		{
			throw new RuntimeException(exception);
		}
	}
	
	private static <T> T newInstance(Class<T> klass, Constructor<T> constructor) throws InvalidClassException
	{
		if (constructor == null)
		{
			throw new InvalidClassException(klass.getName(), "no valid constructor");
		}
		
		constructor.setAccessible(true);
		
		try
		{
			return constructor.newInstance();
		}
		catch (InvocationTargetException exception)
		{
			throw (InvalidClassException) new InvalidClassException(klass.getName(), "unable to create instance")
				.initCause(exception);
		}
		catch (InstantiationException exception)
		{
			throw (InvalidClassException) new InvalidClassException(klass.getName(), "unable to create instance")
				.initCause(exception);
		}
		catch (IllegalAccessException exception)
		{
			throw (InvalidClassException) new InvalidClassException(klass.getName(), "unable to create instance")
				.initCause(exception);
		}
	}
	
	private static boolean isPackageProtected(int modifiers)
	{
		return !Modifier.isPublic(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers);
	}
	
	private static boolean packageEquals(Class<?> class1, Class<?> class2)
	{
		if (class1.getClassLoader() != class2.getClassLoader())
		{
			return false;
		}
		
		return getPackageName(class1).equals(getPackageName(class2));
	}
	
	private static String getPackageName(Class<?> klass)
	{
		String className = klass.getName();
		
		int index = className.lastIndexOf('.');
		
		return (index != -1) ? className.substring(0, index) : "";
	}
}
