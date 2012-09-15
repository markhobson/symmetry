/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/test/SimpleExternalizable.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io.test;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SimpleExternalizable implements Externalizable
{
	// fields -----------------------------------------------------------------
	
	private String name;
	
	// constructors -----------------------------------------------------------
	
	public SimpleExternalizable()
	{
		this(null);
	}
	
	public SimpleExternalizable(String name)
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
		if (name == null)
		{
			name = "";
		}
		
		this.name = name;
	}
	
	// Externalizable methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		out.writeUTF(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		name = in.readUTF();
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
		if (!(object instanceof SimpleExternalizable))
		{
			return false;
		}
		
		SimpleExternalizable that = (SimpleExternalizable) object;
		
		return name.equals(that.getName());
	}
}
