/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDEmptyContentTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>DTDEmptyContent</code>.
 * 
 * @author Mark Hobson
 * @see DTDEmptyContent
 */
public class DTDEmptyContentTest
{
	// fields -----------------------------------------------------------------
	
	private DTDEmptyContent content;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		content = DTDEmptyContent.INSTANCE;
	}
	
	// validate tests ---------------------------------------------------------
	
	@Test
	public void validateEmpty()
	{
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateSingle()
	{
		assertEquals(-1, content.validate(0, "a"));
	}
	
	@Test
	public void validateMultiple()
	{
		assertEquals(-1, content.validate(0, "a", "b", "c"));
	}
	
	// toString tests ---------------------------------------------------------
	
	@Test
	public void toStringTest()
	{
		assertEquals("EMPTY", content.toString());
	}
}
