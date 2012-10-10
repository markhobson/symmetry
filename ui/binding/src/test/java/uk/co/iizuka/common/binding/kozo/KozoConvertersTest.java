/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/test/java/uk/co/iizuka/common/binding/kozo/KozoConvertersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import static org.hobsoft.symmetry.ui.functor.Functions.forMapEntry;
import static org.junit.Assert.assertEquals;

import org.hobsoft.entangle.Converter;
import org.junit.Test;

/**
 * Tests {@code KozoConverters}.
 * 
 * @author Mark Hobson
 * @version $Id: KozoConvertersTest.java 97383 2011-12-28 20:22:56Z mark@IIZUKA.CO.UK $
 * @see KozoConverters
 */
public class KozoConvertersTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void forFunctionConvert()
	{
		Converter<String, Integer> converter = KozoConverters.forFunction(forMapEntry("a", 1));
		
		assertEquals((Integer) 1, converter.convert("a"));
	}
	
	@Test
	public void forFunctionConvertWithSupertypeFrom()
	{
		Converter<String, Integer> converter = KozoConverters.forFunction(forMapEntry((Object) "a", 1));
		
		assertEquals((Integer) 1, converter.convert("a"));
	}
	
	@Test
	public void forFunctionConvertWithSubtypeTo()
	{
		Converter<String, Number> converter = KozoConverters.<String, Number>forFunction(forMapEntry("a", 1));
		
		assertEquals(1, converter.convert("a"));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void forFunctionUnconvert()
	{
		Converter<String, Integer> converter = KozoConverters.forFunction(forMapEntry("a", 1));
		
		converter.unconvert(1);
	}
	
	@Test
	public void forFunctionWithInverseFunctionConvert()
	{
		Converter<String, Integer> converter = KozoConverters.forFunction(forMapEntry("a", 1), forMapEntry(1, "a"));
		
		assertEquals((Integer) 1, converter.convert("a"));
	}
	
	@Test
	public void forFunctionWithInverseFunctionAndSupertypeFromConvert()
	{
		Converter<String, Integer> converter = KozoConverters.forFunction(forMapEntry((Object) "a", 1),
			forMapEntry(1, "a"));
		
		assertEquals((Integer) 1, converter.convert("a"));
	}
	
	@Test
	public void forFunctionWithInverseFunctionAndSubtypeToConvert()
	{
		Converter<String, Number> converter = KozoConverters.<String, Number>forFunction(forMapEntry("a", 1),
			forMapEntry((Number) 1, "a"));
		
		assertEquals(1, converter.convert("a"));
	}
	
	@Test
	public void forFunctionWithInverseFunctionAndInverseSupertypeFromConvert()
	{
		Converter<String, Integer> converter = KozoConverters.forFunction(forMapEntry("a", 1),
			forMapEntry((Number) 1, "a"));
		
		assertEquals((Integer) 1, converter.convert("a"));
	}
	
	@Test
	public void forFunctionWithInverseFunctionAndInverseSubtypeToConvert()
	{
		Converter<Object, Integer> converter = KozoConverters.<Object, Integer>forFunction(forMapEntry((Object) "a", 1),
			forMapEntry(1, "a"));
		
		assertEquals((Integer) 1, converter.convert("a"));
	}
	
	@Test
	public void forFunctionWithInverseFunctionUnconvert()
	{
		Converter<String, Integer> converter = KozoConverters.forFunction(forMapEntry("a", 1), forMapEntry(1, "a"));
		
		assertEquals("a", converter.unconvert(1));
	}
	
	@Test(expected = NullPointerException.class)
	public void forFunctionWithNull()
	{
		KozoConverters.forFunction(null);
	}
}
