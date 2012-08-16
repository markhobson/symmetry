/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/test/CustomSubclassSerializable.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: CustomSubclassSerializable.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class CustomSubclassSerializable extends CustomSerializable
{
	// fields -----------------------------------------------------------------
	
	private int age;
	
	// constructors -----------------------------------------------------------
	
	public CustomSubclassSerializable(String name, int age)
	{
		super(name);
		
		setAge(age);
	}
	
	// public methods ---------------------------------------------------------
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode() * 31 + age;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof CustomSubclassSerializable))
		{
			return false;
		}
		
		CustomSubclassSerializable that = (CustomSubclassSerializable) object;
		
		return super.equals(object) && age == that.getAge();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[name=" + getName() + ",age=" + age + "]";
	}
	
	// Serializable methods ---------------------------------------------------
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		// write age in a non-standard way for test
		
		out.writeInt(age / 10);
		out.writeInt(age % 10);
	}
	
	private void readObject(ObjectInputStream in) throws IOException
	{
		// read age in a non-standard way for test
		
		int quotient = in.readInt();
		int remainder = in.readInt();
		
		age = (quotient * 10) + remainder;
	}
}
