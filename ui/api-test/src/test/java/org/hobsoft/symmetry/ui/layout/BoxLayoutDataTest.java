/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/layout/BoxLayoutDataTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.layout;

import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.layout.Length.flex;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code BoxLayoutData}.
 * 
 * @author Mark Hobson
 * @see BoxLayoutData
 */
public class BoxLayoutDataTest
{
	// fields -----------------------------------------------------------------
	
	private BoxLayoutData data;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		data = new BoxLayoutData();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newBoxLayoutData()
	{
		assertNull("length", data.getLength());
	}
	
	@Test
	public void setLength()
	{
		data.setLength(flex(1));
		
		assertEquals("length", flex(1), data.getLength());
	}
	
	@Test
	public void setLengthWithNull()
	{
		data.setLength(null);
		
		assertNull("length", data.getLength());
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		BoxLayoutData data1 = new BoxLayoutData();
		BoxLayoutData data2 = new BoxLayoutData();
		
		assertEquals(data1.hashCode(), data2.hashCode());
	}
	
	@Test
	public void equalsWithEqual()
	{
		BoxLayoutData data1 = new BoxLayoutData();
		BoxLayoutData data2 = new BoxLayoutData();
		
		assertTrue(data1.equals(data2));
	}
	
	@Test
	public void equalsWithDifferentLength()
	{
		BoxLayoutData data1 = new BoxLayoutData();
		BoxLayoutData data2 = new BoxLayoutData();
		data2.setLength(flex(1));
		
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
		assertEquals("org.hobsoft.symmetry.ui.layout.BoxLayoutData[length=null]", data.toString());
	}
}
