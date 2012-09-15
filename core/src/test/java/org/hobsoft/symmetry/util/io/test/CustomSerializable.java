/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/test/CustomSerializable.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CustomSerializable implements Serializable
{
	// fields -----------------------------------------------------------------
	
	private String name;
	
	// constructors -----------------------------------------------------------
	
	public CustomSerializable(String name)
	{
		setName(name);
	}
	
	// public methods ---------------------------------------------------------
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = (name != null) ? name : "";
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof CustomSerializable))
		{
			return false;
		}
		
		CustomSerializable that = (CustomSerializable) object;
		
		return name.equals(that.getName());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[name=" + name + "]";
	}
	
	// Serializable methods ---------------------------------------------------
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		int length = name.length();
		
		out.writeInt(length);
		
		for (int i = 0; i < length; i++)
		{
			out.writeChar(name.charAt(i));
		}
	}
	
	private void readObject(ObjectInputStream in) throws IOException
	{
		int length = in.readInt();
		
		StringBuilder builder = new StringBuilder();
		
		while (length-- > 0)
		{
			builder.append(in.readChar());
		}
		
		name = builder.toString();
	}
}
