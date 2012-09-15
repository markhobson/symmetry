/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDCardinalContentTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests <code>DTDCardinalContent</code>.
 * 
 * @author Mark Hobson
 * @see DTDCardinalContent
 */
public class DTDCardinalContentTest
{
	// constructor tests ------------------------------------------------------
	
	@Test
	public void constructorDefault()
	{
		DTDCardinalContent content = new MockDTDCardinalContent();
		
		assertEquals(DTDCardinality.ONCE, content.getCardinality());
	}
	
	@Test
	public void constructorCardinality()
	{
		DTDCardinalContent content = new MockDTDCardinalContent(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(DTDCardinality.ONE_OR_MORE, content.getCardinality());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorCardinalityNull()
	{
		try
		{
			new MockDTDCardinalContent(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("cardinality: null", exception.getMessage());
			
			throw exception;
		}
	}
	
	// hashCode tests ---------------------------------------------------------
	
	@Test
	public void hashCodeSameCardinality()
	{
		DTDCardinalContent content1 = new MockDTDCardinalContent();
		DTDCardinalContent content2 = new MockDTDCardinalContent();
		
		assertEquals(content1.hashCode(), content2.hashCode());
	}
	
	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSameCardinality()
	{
		DTDCardinalContent content1 = new MockDTDCardinalContent();
		DTDCardinalContent content2 = new MockDTDCardinalContent();
		
		assertEquals(content1, content2);
		assertEquals(content1.hashCode(), content2.hashCode());
	}
	
	@Test
	public void equalsDifferentCardinality()
	{
		DTDCardinalContent content1 = new MockDTDCardinalContent();
		DTDCardinalContent content2 = new MockDTDCardinalContent(DTDCardinality.ONE_OR_MORE);
		
		assertFalse(content1.equals(content2));
	}
}
