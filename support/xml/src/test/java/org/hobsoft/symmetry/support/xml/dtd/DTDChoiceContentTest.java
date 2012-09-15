/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDChoiceContentTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Tests <code>DTDChoiceContent</code>.
 * 
 * @author Mark Hobson
 * @see DTDChoiceContent
 */
public class DTDChoiceContentTest
{
	// constructor tests ------------------------------------------------------
	
	@Test
	public void constructorDefault()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		
		assertEquals(DTDCardinality.ONCE, content.getCardinality());
	}
	
	@Test
	public void constructorCardinality()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(DTDCardinality.ONE_OR_MORE, content.getCardinality());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorCardinalityNull()
	{
		try
		{
			new DTDChoiceContent(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("cardinality: null", exception.getMessage());
			
			throw exception;
		}
	}
	
	// validate tests ---------------------------------------------------------
	
	@Test
	public void validateOnceSinglePresent()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOnceSinglePresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOnceSingleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOnceSingleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOnceMultipleFirstPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOnceMultipleFirstPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOnceMultipleSecondPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOnceMultipleSecondPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateOnceMultipleLastPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateOnceMultipleLastPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentUnordered()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentUnorderedMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateOnceMultipleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "d"));
	}
	
	@Test
	public void validateOnceMultipleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOncePCDATA()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		
		assertEquals(-1, content.validate(0, (String) null));
	}
	
	@Test
	public void validateOneOrMoreSinglePresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOneOrMoreSinglePresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOneOrMoreSingleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOneOrMoreSingleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOneOrMoreMultipleFirstPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleFirstPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleSecondPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOneOrMoreMultipleSecondPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateOneOrMoreMultipleLastPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleLastPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentUnordered()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentUnorderedMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "d"));
	}
	
	@Test
	public void validateOneOrMoreMultipleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOneOrMorePCDATA()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(-1, content.validate(0, (String) null));
	}
	
	@Test
	public void validateZeroOrMoreSinglePresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrMoreSinglePresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrMoreSingleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrMoreSingleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrMoreMultipleFirstPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleFirstPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleSecondPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleSecondPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleLastPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleLastPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentUnordered()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentUnorderedMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "d"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrMorePCDATA()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(0, content.validate(0, (String) null));
	}
	
	@Test
	public void validateZeroOrOneSinglePresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrOneSinglePresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrOneSingleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrOneSingleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrOneMultipleFirstPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleFirstPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleSecondPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrOneMultipleSecondPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateZeroOrOneMultipleLastPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleLastPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentUnordered()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentUnorderedMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleNotPresent()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "d"));
	}
	
	@Test
	public void validateZeroOrOneMultipleNotPresentEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrOnePCDATA()
	{
		DTDChoiceContent content = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		
		assertEquals(0, content.validate(0, (String) null));
	}
	
	// getSeparator tests -----------------------------------------------------
	
	@Test
	public void getSeparator()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		
		assertEquals("|", content.getSeparator());
	}
	
	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSame()
	{
		DTDChoiceContent content1 = new DTDChoiceContent();
		DTDChoiceContent content2 = new DTDChoiceContent();
		
		assertEquals(content1, content2);
	}
	
	@Test
	public void equalsOtherSubclass()
	{
		DTDChoiceContent content1 = new DTDChoiceContent();
		DTDContentContainer content2 = new MockDTDContentContainer();
		
		assertFalse(content1.equals(content2));
	}
	
	// toString tests ---------------------------------------------------------
	
	@Test
	public void toStringEmpty()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		
		assertEquals("()", content.toString());
	}
	
	@Test
	public void toStringSingle()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals("(a)", content.toString());
	}
	
	@Test
	public void toStringMultiple()
	{
		DTDChoiceContent content = new DTDChoiceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals("(a|b|c)", content.toString());
	}
}
