/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests <code>DTDMixedContent</code>.
 * 
 * @author Mark Hobson
 * @see DTDMixedContent
 */
public class DTDMixedContentTest
{
	// TODO: complete PCDATA tests
	
	// constructor tests ------------------------------------------------------
	
	@Test
	public void constructorDefault()
	{
		DTDMixedContent content = new DTDMixedContent();
		
		assertEquals(DTDCardinality.ONCE, content.getCardinality());
	}
	
	@Test
	public void constructorCardinality()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(DTDCardinality.ONE_OR_MORE, content.getCardinality());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorCardinalityNull()
	{
		try
		{
			new DTDMixedContent(null);
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
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOnceSinglePresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOnceSingleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOnceSingleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOnceMultipleFirstPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOnceMultipleFirstPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOnceMultipleSecondPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOnceMultipleSecondPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateOnceMultipleLastPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateOnceMultipleLastPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentUnordered()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateOnceMultipleAllPresentUnorderedMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateOnceMultipleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "d"));
	}
	
	@Test
	public void validateOnceMultipleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOncePCDATA()
	{
		DTDMixedContent content = new DTDMixedContent();
		
		assertEquals(1, content.validate(0, (String) null));
	}
	
	@Test
	public void validateOncePCDATAMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		
		assertEquals(3, content.validate(0, null, null, null));
	}
	
	@Test
	public void validateOnceMixedPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(3, content.validate(0, null, "a", null));
	}
	
	@Test
	public void validateOnceMixedNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, null, "b", null));
	}
	
	@Test
	public void validateOneOrMoreSinglePresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOneOrMoreSinglePresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOneOrMoreSingleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOneOrMoreSingleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOneOrMoreMultipleFirstPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleFirstPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleSecondPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateOneOrMoreMultipleSecondPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateOneOrMoreMultipleLastPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleLastPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentUnordered()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleAllPresentUnorderedMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateOneOrMoreMultipleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0, "d"));
	}
	
	@Test
	public void validateOneOrMoreMultipleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(-1, content.validate(0));
	}
	
	@Test
	public void validateOneOrMorePCDATA()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(1, content.validate(0, (String) null));
	}
	
	@Test
	public void validateZeroOrMoreSinglePresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrMoreSinglePresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrMoreSingleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrMoreSingleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrMoreMultipleFirstPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleFirstPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleSecondPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleSecondPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleLastPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleLastPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentUnordered()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(3, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleAllPresentUnorderedMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(9, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "d"));
	}
	
	@Test
	public void validateZeroOrMoreMultipleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrMorePCDATA()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(1, content.validate(0, (String) null));
	}
	
	@Test
	public void validateZeroOrOneSinglePresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrOneSinglePresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrOneSingleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrOneSingleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrOneMultipleFirstPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleFirstPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleSecondPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b"));
	}
	
	@Test
	public void validateZeroOrOneMultipleSecondPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "b", "b", "b"));
	}
	
	@Test
	public void validateZeroOrOneMultipleLastPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleLastPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "c", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "a", "b", "c", "a", "b", "c", "a", "b", "c"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentUnordered()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleAllPresentUnorderedMultiple()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(1, content.validate(0, "c", "b", "a", "c", "b", "a", "c", "b", "a"));
	}
	
	@Test
	public void validateZeroOrOneMultipleNotPresent()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0, "d"));
	}
	
	@Test
	public void validateZeroOrOneMultipleNotPresentEmpty()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateZeroOrOnePCDATA()
	{
		DTDMixedContent content = new DTDMixedContent(DTDCardinality.ZERO_OR_ONE);
		
		assertEquals(1, content.validate(0, (String) null));
	}
	
	// getSeparator tests -----------------------------------------------------
	
	@Test
	public void getSeparator()
	{
		DTDMixedContent content = new DTDMixedContent();
		
		assertEquals("|", content.getSeparator());
	}
	
	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSame()
	{
		DTDMixedContent content1 = new DTDMixedContent();
		DTDMixedContent content2 = new DTDMixedContent();
		
		assertEquals(content1, content2);
	}
	
	@Test
	public void equalsOtherSubclass()
	{
		DTDMixedContent content1 = new DTDMixedContent();
		DTDContentContainer content2 = new MockDTDContentContainer();
		
		assertFalse(content1.equals(content2));
	}
	
	// toString tests ---------------------------------------------------------
	
	@Test
	public void toStringEmpty()
	{
		DTDMixedContent content = new DTDMixedContent();
		
		assertEquals("(#PCDATA)", content.toString());
	}
	
	@Test
	public void toStringSingle()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		
		assertEquals("(#PCDATA|a)", content.toString());
	}
	
	@Test
	public void toStringMultiple()
	{
		DTDMixedContent content = new DTDMixedContent();
		content.addChild(new SimpleDTDContent("a"));
		content.addChild(new SimpleDTDContent("b"));
		content.addChild(new SimpleDTDContent("c"));
		
		assertEquals("(#PCDATA|a|b|c)", content.toString());
	}
}