/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/main/java/uk/co/iizuka/kozo/css/CssStyleBuilder.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.css;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: CssStyleBuilder.java 95597 2011-11-28 12:44:19Z mark@IIZUKA.CO.UK $
 */
public class CssStyleBuilder
{
	// fields -----------------------------------------------------------------
	
	private final StringBuilder builder;
	
	// constructors -----------------------------------------------------------
	
	public CssStyleBuilder()
	{
		this(new StringBuilder());
	}
	
	public CssStyleBuilder(StringBuilder builder)
	{
		if (builder == null)
		{
			throw new IllegalArgumentException("builder cannot be null");
		}
		
		this.builder = builder;
	}
	
	// public methods ---------------------------------------------------------
	
	public CssStyleBuilder append(Css.Property property, Css.Value value)
	{
		append(property, value.toCss());
		
		return this;
	}
	
	public CssStyleBuilder append(Css.Property property, int value, Css.Unit unit)
	{
		append(property, Integer.toString(value));
		builder.append(unit.toCss());
		
		return this;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return builder.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private CssStyleBuilder append(Css.Property property, String value)
	{
		if (builder.length() > 0)
		{
			builder.append("; ");
		}
		
		builder.append(property.toCss());
		builder.append(": ");
		builder.append(value);
		
		return this;
	}
}
