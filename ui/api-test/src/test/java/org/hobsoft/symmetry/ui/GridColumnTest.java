/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/GridColumnTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.layout.Length.pixels;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code GridColumn}.
 * 
 * @author Mark Hobson
 * @see GridColumn
 */
public class GridColumnTest
{
	// fields -----------------------------------------------------------------
	
	private GridColumn column;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		column = new GridColumn();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newGridColumn()
	{
		assertNull("width", column.getWidth());
	}
	
	@Test
	public void setWidthWithLength()
	{
		column.setWidth(pixels(1));
		
		assertEquals("width", pixels(1), column.getWidth());
	}
	
	@Test
	public void setWidthWithNull()
	{
		column.setWidth(null);
		
		assertNull("width", column.getWidth());
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		GridColumn data1 = new GridColumn();
		GridColumn data2 = new GridColumn();
		
		assertEquals(data1.hashCode(), data2.hashCode());
	}
	
	@Test
	public void equalsWithEqual()
	{
		GridColumn data1 = new GridColumn();
		GridColumn data2 = new GridColumn();
		
		assertTrue(data1.equals(data2));
	}
	
	@Test
	public void equalsWithDifferentWidth()
	{
		GridColumn data1 = new GridColumn();
		GridColumn data2 = new GridColumn();
		data2.setWidth(pixels(1));
		
		assertFalse(data1.equals(data2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		assertFalse(column.equals(new Object()));
	}
	
	@Test
	public void equalsWithNull()
	{
		assertFalse(column.equals(null));
	}
	
	@Test
	public void toStringTest()
	{
		String expected = "org.hobsoft.symmetry.ui.GridColumn[width=null]";
		
		assertEquals(expected, column.toString());
	}
}
