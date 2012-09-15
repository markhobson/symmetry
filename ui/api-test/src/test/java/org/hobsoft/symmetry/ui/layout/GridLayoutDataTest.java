/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/layout/GridLayoutDataTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests {@code GridLayoutData}.
 * 
 * @author Mark Hobson
 * @version $Id: GridLayoutDataTest.java 99813 2012-03-22 17:33:54Z mark@IIZUKA.CO.UK $
 * @see GridLayoutData
 */
public class GridLayoutDataTest
{
	// fields -----------------------------------------------------------------
	
	private GridLayoutData data;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		data = new GridLayoutData();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newGridLayoutData()
	{
		assertEquals(HorizontalAlignment.LEFT, data.getHorizontalAlignment());
		assertEquals(VerticalAlignment.MIDDLE, data.getVerticalAlignment());
		assertEquals(1, data.getColumnSpan());
	}
	
	@Test
	public void setHorizontalAlignment()
	{
		data.setHorizontalAlignment(HorizontalAlignment.MIDDLE);
		
		assertEquals(HorizontalAlignment.MIDDLE, data.getHorizontalAlignment());
	}
	
	@Test(expected = NullPointerException.class)
	public void setHorizontalAlignmentWithNull()
	{
		data.setHorizontalAlignment(null);
	}
	
	@Test
	public void setVerticalAlignment()
	{
		data.setVerticalAlignment(VerticalAlignment.TOP);
		
		assertEquals(VerticalAlignment.TOP, data.getVerticalAlignment());
	}
	
	@Test(expected = NullPointerException.class)
	public void setVerticalAlignmentWithNull()
	{
		data.setVerticalAlignment(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnSpanWithNegative()
	{
		data.setColumnSpan(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnSpanWithZero()
	{
		data.setColumnSpan(0);
	}
	
	@Test
	public void setColumnSpanWithPositive()
	{
		data.setColumnSpan(1);
		
		assertEquals(1, data.getColumnSpan());
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		GridLayoutData data1 = new GridLayoutData();
		GridLayoutData data2 = new GridLayoutData();
		
		assertEquals(data1.hashCode(), data2.hashCode());
	}
	
	@Test
	public void equalsWithEqual()
	{
		GridLayoutData data1 = new GridLayoutData();
		GridLayoutData data2 = new GridLayoutData();
		
		assertTrue(data1.equals(data2));
	}
	
	@Test
	public void equalsWithDifferentHorizontalAlignment()
	{
		GridLayoutData data1 = new GridLayoutData();
		GridLayoutData data2 = new GridLayoutData();
		data2.setHorizontalAlignment(HorizontalAlignment.MIDDLE);
		
		assertFalse(data1.equals(data2));
	}
	
	@Test
	public void equalsWithDifferentVerticalAlignment()
	{
		GridLayoutData data1 = new GridLayoutData();
		GridLayoutData data2 = new GridLayoutData();
		data2.setVerticalAlignment(VerticalAlignment.TOP);
		
		assertFalse(data1.equals(data2));
	}
	
	@Test
	public void equalsWithDifferentColumnSpan()
	{
		GridLayoutData data1 = new GridLayoutData();
		GridLayoutData data2 = new GridLayoutData();
		data2.setColumnSpan(2);
		
		assertFalse(data1.equals(data2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		assertFalse(data.equals(new Object()));
	}
	
	@Test
	public void equalsWithNull()
	{
		assertFalse(data.equals(null));
	}
	
	@Test
	public void toStringTest()
	{
		String expected = "org.hobsoft.symmetry.ui.layout.GridLayoutData[horizontalAlignment=LEFT, "
			+ "verticalAlignment=MIDDLE, columnSpan=1]";
		
		assertEquals(expected, data.toString());
	}
}
