/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/test/java/uk/co/iizuka/kozo/hydrate/RehydrationContextTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.hydrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import uk.co.iizuka.kozo.state.EncodedState;

/**
 * Tests {@code RehydrationContext}.
 * 
 * @author Mark Hobson
 * @version $Id: RehydrationContextTest.java 95595 2011-11-28 12:38:59Z mark@IIZUKA.CO.UK $
 * @see RehydrationContext
 */
public class RehydrationContextTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		RehydrationContext context = createContext();
		
		assertEquals(new EncodedState(), context.getEncodedState());
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		RehydrationContext context1 = createFullContext();
		RehydrationContext context2 = createFullContext();
		
		assertEquals(context1.hashCode(), context2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		RehydrationContext context1 = createFullContext();
		RehydrationContext context2 = createFullContext();
		
		assertTrue(context1.equals(context2));
	}
	
	@Test
	public void equalsWhenEncodedStateDifferent()
	{
		RehydrationContext context1 = createFullContext();
		context1.setEncodedState(new EncodedState());
		
		RehydrationContext context2 = createFullContext();
		
		assertFalse(context1.equals(context2));
	}
	
	@Test
	public void equalsWhenParametersDifferent()
	{
		RehydrationContext context1 = createFullContext();
		context1.set(Long.class, 2L);
		
		RehydrationContext context2 = createFullContext();
		
		assertFalse(context1.equals(context2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		RehydrationContext context = createFullContext();
		
		assertFalse(context.equals(new Object()));
	}
	
	@Test
	public void equalsWithNull()
	{
		RehydrationContext context = createFullContext();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(context.equals(null));
		// CHECKSTYLE:ON
	}
	
	// private methods --------------------------------------------------------
	
	private static RehydrationContext createContext()
	{
		return new RehydrationContext();
	}
	
	private static RehydrationContext createFullContext()
	{
		RehydrationContext context = createContext();
		context.setEncodedState(createFullEncodedState());
		context.set(String.class, "a");
		context.set(Integer.class, 1);
		
		return context;
	}
	
	private static EncodedState createFullEncodedState()
	{
		Map<String, List<Object>> parameters = new HashMap<String, List<Object>>();
		parameters.put("a", Arrays.<Object>asList(1, 2));
		parameters.put("b", Arrays.<Object>asList(3, 4));
		
		return new EncodedState("x", parameters);
	}
}
