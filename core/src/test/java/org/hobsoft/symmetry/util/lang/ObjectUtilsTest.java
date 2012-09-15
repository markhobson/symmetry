/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/lang/ObjectUtilsTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ObjectUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void nonInstantiable()
	{
		Constructor<?>[] constructors = ObjectUtils.class.getConstructors();
		
		for (int i = 0; i < constructors.length; i++)
		{
			assertTrue(Modifier.isPrivate(constructors[i].getModifiers()));
		}
	}
	
	@Test
	public void equalsOrNullBothNull()
	{
		assertTrue(ObjectUtils.equals(null, null));
	}
	
	@Test
	public void equalsOrNullFirstNull()
	{
		assertFalse(ObjectUtils.equals(null, new Object()));
	}
	
	@Test
	public void equalsOrNullSecondNull()
	{
		assertFalse(ObjectUtils.equals(new Object(), null));
	}
	
	@Test
	public void equalsOrNullSame()
	{
		Object object = new Object();
		assertTrue(ObjectUtils.equals(object, object));
	}
	
	@Test
	public void equalsOrNullEqual()
	{
		assertTrue(ObjectUtils.equals(123, 123));
	}
}
