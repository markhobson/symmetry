/*
 * $HeadURL: https://svn.iizuka.co.uk/common/test/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/test/PrivateAccessor.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.test;

import java.lang.reflect.Field;

import org.junit.Assert;

/**
 * Provides access to private members in classes.
 * 
 * @author Mark Hobson
 * @version $Id: PrivateAccessor.java 69783 2010-01-21 11:40:28Z mark@IIZUKA.CO.UK $
 * @see <a href="http://junit.sourceforge.net/doc/faq/faq.htm#tests_11">JUnit FAQ</a>
 * @see <a href="http://www.onjava.com/pub/a/onjava/2003/11/12/reflection.html">PrivilegedAccessor</a>
 */
public final class PrivateAccessor
{
	// constructors -----------------------------------------------------------
	
	private PrivateAccessor()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------

	public static Object getPrivateField(Object object, String fieldName)
	{
		// check we have valid arguments
		Assert.assertNotNull(object);
		Assert.assertNotNull(fieldName);
		
		// go and find the private field...
		for (Field field : object.getClass().getDeclaredFields())
		{
			if (fieldName.equals(field.getName()))
			{
				try
				{
					field.setAccessible(true);
					
					return field.get(object);
				}
				catch (IllegalAccessException exception)
				{
					Assert.fail("IllegalAccessException accessing field: " + fieldName);
				}
			}
		}
		
		Assert.fail("Field not found: " + fieldName);
		
		return null;
	}
}
