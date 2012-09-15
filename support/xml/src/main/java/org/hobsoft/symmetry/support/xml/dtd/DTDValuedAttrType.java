/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDValuedAttrType.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An abstract DTD attribute type that can hold a list of string values.
 * 
 * @author Mark Hobson
 */
public abstract class DTDValuedAttrType implements DTDAttrType, Iterable<String>
{
	// fields -----------------------------------------------------------------
	
	private final List<String> values;
	
	// constructors -----------------------------------------------------------
	
	public DTDValuedAttrType()
	{
		values = new ArrayList<String>();
	}
	
	// Iterable methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<String> iterator()
	{
		return getValues().iterator();
	}
	
	// public methods ---------------------------------------------------------
	
	public void addValue(String value)
	{
		values.add(value);
	}
	
	public void removeValue(String value)
	{
		values.remove(value);
	}
	
	public List<String> getValues()
	{
		return Collections.unmodifiableList(values);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return values.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDValuedAttrType))
		{
			return false;
		}
		
		DTDValuedAttrType enumAttrType = (DTDValuedAttrType) object;
		
		return values.equals(enumAttrType.values);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		for (String value : this)
		{
			if (builder.length() > 0)
			{
				builder.append("|");
			}
			
			builder.append(value);
		}
		
		builder.insert(0, "(");
		builder.append(")");
		
		return builder.toString();
	}
}
