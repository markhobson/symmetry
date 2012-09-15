/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/test/java/uk/co/iizuka/kozo/hydrate/MediaTypesTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests {@code MediaTypes}.
 * 
 * @author Mark Hobson
 * @version $Id: MediaTypesTest.java 95600 2011-11-28 12:54:36Z mark@IIZUKA.CO.UK $
 * @see MediaTypes
 */
public class MediaTypesTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void getParameterValueWhenNotPresent()
	{
		assertNull(MediaTypes.getParameterValue("a/b", "p"));
	}
	
	@Test
	public void getParameterValueWhenPresent()
	{
		assertEquals("x", MediaTypes.getParameterValue("a/b;p=x", "p"));
	}

	@Test
	public void getParameterValueWhenPresentWithQuotedValue()
	{
		assertEquals("x", MediaTypes.getParameterValue("a/b;p=\"x\"", "p"));
	}
	
	@Test
	public void getParameterValueWhenPresentWithMissingValue()
	{
		assertEquals("", MediaTypes.getParameterValue("a/b;p", "p"));
	}
	
	@Test
	public void getParameterValueWhenPresentWithWhitespace()
	{
		assertEquals("x", MediaTypes.getParameterValue("a/b ; p = x ", "p"));
	}
	
	@Test
	public void getParameterValueWithEmptyMediaType()
	{
		assertNull(MediaTypes.getParameterValue("", "p"));
	}
	
	@Test(expected = NullPointerException.class)
	public void getParameterValueWithNullMediaType()
	{
		assertNull(MediaTypes.getParameterValue(null, "p"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getParameterValueWithEmptyParameterName()
	{
		assertNull(MediaTypes.getParameterValue("a/b", ""));
	}
	
	@Test(expected = NullPointerException.class)
	public void getParameterValueWithNullParameterName()
	{
		assertNull(MediaTypes.getParameterValue("a/b", null));
	}
}
