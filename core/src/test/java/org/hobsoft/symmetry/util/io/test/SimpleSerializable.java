/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/test/SimpleSerializable.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io.test;

import java.io.Serializable;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SimpleSerializable.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class SimpleSerializable implements Serializable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 7987891414315418494L;
	
	// fields -----------------------------------------------------------------
	
	private String name;
	
	// constructors -----------------------------------------------------------
	
	public SimpleSerializable(String name)
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
		if (!(object instanceof SimpleSerializable))
		{
			return false;
		}
		
		SimpleSerializable that = (SimpleSerializable) object;
		
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
}
