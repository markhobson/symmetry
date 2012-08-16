/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/test/java/uk/co/iizuka/kozo/hydrate/HydrationContextTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.hydrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests {@code HydrationContext}.
 * 
 * @author Mark Hobson
 * @version $Id: HydrationContextTest.java 95595 2011-11-28 12:38:59Z mark@IIZUKA.CO.UK $
 * @see HydrationContext
 */
public class HydrationContextTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		HydrationContext context = createContext();
		
		assertEquals(Collections.emptySet(), context.getParameterTypes());
	}
	
	@Test
	public void getWhenPresent()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		
		assertEquals("a", context.get(String.class));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getWhenNotPresent()
	{
		HydrationContext context = createContext();
		
		context.get(String.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void getWithNullParameterType()
	{
		HydrationContext context = createContext();
		
		context.get(null);
	}
	
	@Test
	public void getWithDefaultWhenPresent()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		
		assertEquals("a", context.get(String.class, "b"));
	}
	
	@Test
	public void getWithDefaultWhenNotPresent()
	{
		HydrationContext context = createContext();
		
		assertEquals("b", context.get(String.class, "b"));
	}
	
	@Test(expected = NullPointerException.class)
	public void getWithDefaultWithNullParameterType()
	{
		HydrationContext context = createContext();
		
		context.get(null, "a");
	}
	
	@Test
	public void getOrSetWhenPresent()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		
		assertEquals("a", context.getOrSet(String.class, "b"));
	}
	
	@Test
	public void getOrSetWhenNotPresentGets()
	{
		HydrationContext context = createContext();
		
		assertEquals("b", context.getOrSet(String.class, "b"));
	}
	
	@Test
	public void getOrSetWhenNotPresentSets()
	{
		HydrationContext context = createContext();
		
		context.getOrSet(String.class, "b");
		
		assertEquals("b", context.get(String.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void getOrSetWithNullParameterType()
	{
		HydrationContext context = createContext();
		
		context.getOrSet(null, "a");
	}
	
	@Test(expected = NullPointerException.class)
	public void getOrSetWithNullParameter()
	{
		HydrationContext context = createContext();
		
		context.getOrSet(String.class, null);
	}
	
	@Test
	public void getAllWhenEmpty()
	{
		HydrationContext context = createContext();
		
		assertEquals(Collections.emptyMap(), context.getAll());
	}
	
	@Test
	public void getAllWhenSingleParameter()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		
		assertEquals(Collections.singletonMap(String.class, "a"), context.getAll());
	}
	
	@Test
	public void getAllWhenMultipleParameters()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		context.set(Integer.class, 1);
		
		assertEquals(createMap(String.class, "a", Integer.class, 1), context.getAll());
	}
	
	@Test(expected = NullPointerException.class)
	public void setWithNullParameterType()
	{
		HydrationContext context = createContext();
		
		context.set(null, "a");
	}
	
	@Test(expected = NullPointerException.class)
	public void setWithNullParameter()
	{
		HydrationContext context = createContext();
		
		context.set(String.class, null);
	}
	
	@Test
	public void setAllWithEmptyMap()
	{
		HydrationContext context = createContext();
		
		context.setAll(Collections.<Class<?>, Object>emptyMap());
		
		assertEquals(Collections.emptyMap(), context.getAll());
	}
	
	@Test
	public void setAllWithNull()
	{
		HydrationContext context = createContext();
		
		context.setAll(null);
		
		assertEquals(Collections.emptyMap(), context.getAll());
	}
	
	@Test
	public void setAllWithSingleParameterMap()
	{
		HydrationContext context = createContext();
		
		context.setAll(Collections.singletonMap(String.class, "a"));
		
		assertEquals(Collections.singletonMap(String.class, "a"), context.getAll());
	}
	
	@Test
	public void setAllWithMultipleParametersMap()
	{
		HydrationContext context = createContext();
		
		context.setAll(createMap(String.class, "a", Integer.class, 1));
		
		assertEquals(createMap(String.class, "a", Integer.class, 1), context.getAll());
	}
	
	@Test
	public void pushWhenNotPresent()
	{
		HydrationContext context = createContext();
		
		context.push(String.class, "a");
		
		assertEquals("a", context.get(String.class));
	}
	
	@Test
	public void pushWhenPresent()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		
		context.push(String.class, "b");
		
		assertEquals("b", context.get(String.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void pushWithNullParameterType()
	{
		HydrationContext context = createContext();
		
		context.push(null, "a");
	}
	
	@Test(expected = NullPointerException.class)
	public void pushWithNullParameter()
	{
		HydrationContext context = createContext();
		
		context.push(String.class, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void popWhenNotPresent()
	{
		HydrationContext context = createContext();
		
		context.pop(String.class);
	}
	
	@Test
	public void popWhenPresent()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		
		assertEquals("a", context.pop(String.class));
	}
	
	@Test
	public void popRecoversPreviousValue()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		context.push(String.class, "b");
		
		context.pop(String.class);
		
		assertEquals("a", context.get(String.class));
	}
	
	@Test
	public void popWhenLastRemovesParameter()
	{
		HydrationContext context = createContext();
		context.set(String.class, "a");
		
		context.pop(String.class);
		
		assertEquals(Collections.emptySet(), context.getParameterTypes());
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		HydrationContext context1 = createFullContext();
		HydrationContext context2 = createFullContext();
		
		assertEquals(context1.hashCode(), context2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		HydrationContext context1 = createFullContext();
		HydrationContext context2 = createFullContext();
		
		assertTrue(context1.equals(context2));
	}
	
	@Test
	public void equalsWhenParametersDifferent()
	{
		HydrationContext context1 = createFullContext();
		context1.set(Long.class, 2L);
		
		HydrationContext context2 = createFullContext();
		
		assertFalse(context1.equals(context2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		HydrationContext context = createFullContext();
		
		assertFalse(context.equals(new Object()));
	}
	
	@Test
	public void equalsWithNull()
	{
		HydrationContext context = createFullContext();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(context.equals(null));
		// CHECKSTYLE:ON
	}
	
	// private methods --------------------------------------------------------
	
	private static HydrationContext createContext()
	{
		return new FakeHydrationContext();
	}
	
	private static HydrationContext createFullContext()
	{
		HydrationContext rehydrationContext = createContext();
		rehydrationContext.set(String.class, "a");
		rehydrationContext.set(Integer.class, 1);
		
		return rehydrationContext;
	}
	
	private static <K, V> Map<K, V> createMap(K key1, V value1, K key2, V value2)
	{
		Map<K, V> map = new HashMap<K, V>();
		map.put(key1, value1);
		map.put(key2, value2);
		return map;
	}
}
