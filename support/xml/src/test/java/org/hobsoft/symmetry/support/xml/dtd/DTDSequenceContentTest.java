/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDSequenceContentTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Tests <code>DTDSequenceContent</code>.
 * 
 * @author Mark Hobson
 * @version $Id: DTDSequenceContentTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see DTDSequenceContent
 */
public class DTDSequenceContentTest
{
	// constructor tests ------------------------------------------------------
	
	@Test
	public void constructorDefault()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		
		assertEquals(DTDCardinality.ONCE, content.getCardinality());
	}
	
	@Test
	public void constructorCardinality()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(DTDCardinality.ONE_OR_MORE, content.getCardinality());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorCardinalityNull()
	{
		try
		{
			new DTDSequenceContent(null);
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
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOnceSinglePresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOnceSingleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOnceSingleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOnceMultipleFirstPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOnceMultipleFirstPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOnceMultipleSecondPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOnceMultipleSecondPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateOnceMultipleLastPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c"));
	}
	
	@Test
	public void validateOnceMultipleLastPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentUnordered()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentUnorderedMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateOnceMultipleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "d"));
	}
	
	@Test
	public void validateOnceMultipleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOncePCDATA()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		
		assertEquals(-1, content.validate(0, (String) null));
	}
	
	@Test
	public void validateOneOrMoreSinglePresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOneOrMoreSinglePresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOneOrMoreSingleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOneOrMoreSingleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOneOrMoreMultipleFirstPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleFirstPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleSecondPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOneOrMoreMultipleSecondPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateOneOrMoreMultipleLastPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleLastPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentUnordered()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentUnorderedMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "d"));
	}
	
	@Test
	public void validateOneOrMoreMultipleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOneOrMorePCDATA()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(-1, content.validate(0, (String) null));
	}
	
	@Test
	public void validateZeroOrMoreSinglePresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrMoreSinglePresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrMoreSingleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrMoreSingleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrMoreMultipleFirstPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleFirstPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleSecondPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleSecondPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleLastPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleLastPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentUnordered()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentUnorderedMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "d"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrMorePCDATA()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(0, content.validate(0, (String) null));
	}
	
	@Test
	public void validateZeroOrOneSinglePresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrOneSinglePresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrOneSingleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrOneSingleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrOneMultipleFirstPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleFirstPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleSecondPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrOneMultipleSecondPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateZeroOrOneMultipleLastPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleLastPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentUnordered()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentUnorderedMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleNotPresent()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "d"));
	}
	
	@Test
	public void validateZeroOrOneMultipleNotPresentEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrOnePCDATA()
	{
		DTDSequenceContent content = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		
		assertEquals(0, content.validate(0, (String) null));
	}
	
	// getSeparator tests -----------------------------------------------------
	
	@Test
	public void getSeparator()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		
		assertEquals(",", content.getSeparator());
	}
	
	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSame()
	{
		DTDSequenceContent content1 = new DTDSequenceContent();
		DTDSequenceContent content2 = new DTDSequenceContent();
		
		assertEquals(content1, content2);
	}
	
	@Test
	public void equalsOtherSubclass()
	{
		DTDSequenceContent content1 = new DTDSequenceContent();
		DTDContentContainer content2 = new MockDTDContentContainer();
		
		assertFalse(content1.equals(content2));
	}
	
	// toString tests ---------------------------------------------------------
	
	@Test
	public void toStringEmpty()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		
		assertEquals("()", content.toString());
	}
	
	@Test
	public void toStringSingle()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals("(a)", content.toString());
	}
	
	@Test
	public void toStringMultiple()
	{
		DTDSequenceContent content = new DTDSequenceContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals("(a,b,c)", content.toString());
	}
}
