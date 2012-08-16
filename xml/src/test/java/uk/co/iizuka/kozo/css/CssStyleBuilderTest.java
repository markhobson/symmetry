/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/test/java/uk/co/iizuka/kozo/css/CssStyleBuilderTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.css;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code CssStyleBuilder}.
 * 
 * @author Mark Hobson
 * @version $Id: CssStyleBuilderTest.java 95597 2011-11-28 12:44:19Z mark@IIZUKA.CO.UK $
 * @see CssStyleBuilder
 */
public class CssStyleBuilderTest
{
	// fields -----------------------------------------------------------------
	
	private CssStyleBuilder builder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		builder = new CssStyleBuilder();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void appendIntProperty()
	{
		builder.append(Css.Property.MARGIN, 10, Css.Unit.PX);
		
		assertEquals("margin: 10px", builder.toString());
	}
	
	@Test
	public void appendIntPropertyTwice()
	{
		builder.append(Css.Property.MARGIN, 10, Css.Unit.PX);
		builder.append(Css.Property.PADDING, 20, Css.Unit.EMS);
		
		assertEquals("margin: 10px; padding: 20em", builder.toString());
	}
	
	@Test
	public void appendValueProperty()
	{
		builder.append(Css.Property.VERTICAL_ALIGN, Css.Value.TOP);
		
		assertEquals("vertical-align: top", builder.toString());
	}
	
	@Test
	public void appendWithContent()
	{
		builder = new CssStyleBuilder(new StringBuilder("margin: 10px"));
		
		builder.append(Css.Property.PADDING, 20, Css.Unit.EMS);
		
		assertEquals("margin: 10px; padding: 20em", builder.toString());
	}
}
