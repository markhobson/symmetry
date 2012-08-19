/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/SimpleDTDContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * A simple name-matching <code>DTDContent</code> implementation for use with testing <code>DTDContentContainer</code>s.
 * 
 * @author Mark Hobson
 * @version $Id: SimpleDTDContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class SimpleDTDContent implements DTDContent
{
	// fields -----------------------------------------------------------------
	
	private final String name;

	// constructors -----------------------------------------------------------
	
	public SimpleDTDContent(String name)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("name: null");
		}
		
		this.name = name;
	}
	
	// DTDContent methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public int validate(int index, String... elements)
	{
		if (index < elements.length && getName().equals(elements[index]))
		{
			index++;
		}
		else
		{
			index = -1;
		}
		
		return index;
	}

	// public methods ---------------------------------------------------------
	
	public String getName()
	{
		return name;
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
		if (!(object instanceof SimpleDTDContent))
		{
			return false;
		}
		
		SimpleDTDContent content = (SimpleDTDContent) object;
		
		return name.equals(content.name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return name;
	}
}
