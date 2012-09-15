/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/main/java/uk/co/iizuka/kozo/css/CssClassBuilder.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.css;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CssClassBuilder
{
	// fields -----------------------------------------------------------------
	
	private final StringBuilder builder;
	
	// constructors -----------------------------------------------------------
	
	public CssClassBuilder()
	{
		this(new StringBuilder());
	}
	
	public CssClassBuilder(StringBuilder builder)
	{
		if (builder == null)
		{
			throw new IllegalArgumentException("builder cannot be null");
		}
		
		this.builder = builder;
	}
	
	public CssClassBuilder(String cssClass)
	{
		this();
		
		append(cssClass);
	}
	
	// public methods ---------------------------------------------------------
	
	public CssClassBuilder append(String cssClass)
	{
		if (builder.length() > 0)
		{
			builder.append(' ');
		}
		
		builder.append(cssClass);
		
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
}
