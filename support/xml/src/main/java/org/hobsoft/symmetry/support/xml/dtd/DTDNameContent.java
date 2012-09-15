/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDNameContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * A DTD element content model that represents an element with a corresponding cardinality.
 * 
 * @author Mark Hobson
 * @version $Id: DTDNameContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDNameContent extends DTDCardinalContent
{
	// fields -----------------------------------------------------------------
	
	private final String name;
	
	// constructors -----------------------------------------------------------
	
	public DTDNameContent(String name)
	{
		this(name, DTDCardinality.ONCE);
	}
	
	public DTDNameContent(String name, DTDCardinality cardinality)
	{
		super(cardinality);
		
		if (name == null)
		{
			throw new IllegalArgumentException("name: null");
		}
		
		this.name = name;
	}
	
	// DTDCardinalContent methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int validateOnce(int index, String... elements)
	{
		return (index < elements.length && getName().equals(elements[index])) ? index + 1 : -1;
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
		return name.hashCode() * 31 + getCardinality().hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDNameContent))
		{
			return false;
		}
		
		DTDNameContent content = (DTDNameContent) object;
		
		return content.getName().equals(name) && content.getCardinality() == getCardinality();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return name + getCardinality();
	}
}
