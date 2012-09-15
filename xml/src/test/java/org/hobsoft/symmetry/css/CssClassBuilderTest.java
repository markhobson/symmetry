/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/test/java/uk/co/iizuka/kozo/css/CssClassBuilderTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.css;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code CssClassBuilder}.
 * 
 * @author Mark Hobson
 * @see CssClassBuilder
 */
public class CssClassBuilderTest
{
	// fields -----------------------------------------------------------------
	
	private CssClassBuilder builder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		builder = new CssClassBuilder();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void appendOnce()
	{
		builder.append("a");
		
		assertEquals("a", builder.toString());
	}
	
	@Test
	public void appendTwice()
	{
		builder.append("a");
		builder.append("b");
		
		assertEquals("a b", builder.toString());
	}
	
	@Test
	public void appendAfterStringBuilderConstruction()
	{
		builder = new CssClassBuilder(new StringBuilder("a"));
		
		builder.append("b");
		
		assertEquals("a b", builder.toString());
	}
	
	@Test
	public void appendAfterStringConstruction()
	{
		builder = new CssClassBuilder("a");
		
		builder.append("b");
		
		assertEquals("a b", builder.toString());
	}
}
