/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/functor/BooleanFunctionsTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code BooleanFunctions}.
 * 
 * @author Mark Hobson
 * @see BooleanFunctions
 */
public class BooleanFunctionsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void trueFalseWithTrue()
	{
		assertEquals("True", BooleanFunctions.trueFalse().apply(Boolean.TRUE));
	}
	
	@Test
	public void trueFalseWithFalse()
	{
		assertEquals("False", BooleanFunctions.trueFalse().apply(Boolean.FALSE));
	}
	
	@Test
	public void trueFalseWithNull()
	{
		assertNull(BooleanFunctions.trueFalse().apply(null));
	}
	
	@Test
	public void yesNoWithTrue()
	{
		assertEquals("Yes", BooleanFunctions.yesNo().apply(Boolean.TRUE));
	}
	
	@Test
	public void yesNoWithFalse()
	{
		assertEquals("No", BooleanFunctions.yesNo().apply(Boolean.FALSE));
	}
	
	@Test
	public void yesNoWithNull()
	{
		assertNull(BooleanFunctions.yesNo().apply(null));
	}
	
	@Test
	public void onOffWithTrue()
	{
		assertEquals("On", BooleanFunctions.onOff().apply(Boolean.TRUE));
	}
	
	@Test
	public void onOffWithFalse()
	{
		assertEquals("Off", BooleanFunctions.onOff().apply(Boolean.FALSE));
	}
	
	@Test
	public void onOffWithNull()
	{
		assertNull(BooleanFunctions.onOff().apply(null));
	}
	
	@Test
	public void oneZeroWithTrue()
	{
		assertEquals("1", BooleanFunctions.oneZero().apply(Boolean.TRUE));
	}
	
	@Test
	public void oneZeroWithFalse()
	{
		assertEquals("0", BooleanFunctions.oneZero().apply(Boolean.FALSE));
	}
	
	@Test
	public void oneZeroWithNull()
	{
		assertNull(BooleanFunctions.oneZero().apply(null));
	}
}
