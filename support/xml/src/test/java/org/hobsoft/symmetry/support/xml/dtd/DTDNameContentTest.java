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
 * Tests <code>DTDNameContent</code>.
 * 
 * @author Mark Hobson
 * @see DTDNameContent
 */
public class DTDNameContentTest
{
	// constructor tests ------------------------------------------------------
	
	@Test
	public void constructorName()
	{
		DTDNameContent content = new DTDNameContent("a");
		
		assertEquals("a", content.getName());
		assertEquals(DTDCardinality.ONCE, content.getCardinality());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorNameNull()
	{
		try
		{
			new DTDNameContent(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("name: null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void constructorNameCardinality()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ONE_OR_MORE);
		
		assertEquals("a", content.getName());
		assertEquals(DTDCardinality.ONE_OR_MORE, content.getCardinality());
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorNameNullCardinality()
	{
		try
		{
			new DTDNameContent(null, DTDCardinality.ONE_OR_MORE);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("name: null", exception.getMessage());
			
			throw exception;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorNameCardinalityNull()
	{
		try
		{
			new DTDNameContent("a", null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("cardinality: null", exception.getMessage());
			
			throw exception;
		}
	}
	
	// validate tests ---------------------------------------------------------
	
	@Test
	public void validateOncePresent()
	{
		DTDNameContent content = new DTDNameContent("a");
		
		assertEquals(1, content.validate(0, "a"));
	}

	@Test
	public void validateOncePresentMultiple()
	{
		DTDNameContent content = new DTDNameContent("a");
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}

	@Test
	public void validateOnceNotPresent()
	{
		DTDNameContent content = new DTDNameContent("a");
		
		assertEquals(-1, content.validate(0, "b"));
	}

	@Test
	public void validateOnceNotPresentEmpty()
	{
		DTDNameContent content = new DTDNameContent("a");
		
		assertEquals(-1, content.validate(0));
	}

	@Test
	public void validateOneOrMorePresent()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ONE_OR_MORE);
		
		assertEquals(1, content.validate(0, "a"));
	}

	@Test
	public void validateOneOrMorePresentMultiple()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ONE_OR_MORE);
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}

	@Test
	public void validateOneOrMoreNotPresent()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ONE_OR_MORE);
		
		assertEquals(-1, content.validate(0, "b"));
	}

	@Test
	public void validateOneOrMoreNotPresentEmpty()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ONE_OR_MORE);
		
		assertEquals(-1, content.validate(0));
	}

	@Test
	public void validateZeroOrMorePresent()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(1, content.validate(0, "a"));
	}

	@Test
	public void validateZeroOrMorePresentMultiple()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(3, content.validate(0, "a", "a", "a"));
	}

	@Test
	public void validateZeroOrMoreNotPresent()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(0, content.validate(0, "b"));
	}

	@Test
	public void validateZeroOrMoreNotPresentEmpty()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(0, content.validate(0));
	}

	@Test
	public void validateZeroOrOnePresent()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_ONE);
		
		assertEquals(1, content.validate(0, "a"));
	}

	@Test
	public void validateZeroOrOnePresentMultiple()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_ONE);
		
		assertEquals(1, content.validate(0, "a", "a", "a"));
	}

	@Test
	public void validateZeroOrOneNotPresent()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_ONE);
		
		assertEquals(0, content.validate(0, "b"));
	}

	@Test
	public void validateZeroOrOneNotPresentEmpty()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ZERO_OR_ONE);
		
		assertEquals(0, content.validate(0));
	}

	// hashCode tests ---------------------------------------------------------
	
	@Test
	public void hashCodeSameNameSameCardinality()
	{
		DTDNameContent content1 = new DTDNameContent("a");
		DTDNameContent content2 = new DTDNameContent("a");
		
		assertEquals(content1.hashCode(), content2.hashCode());
	}

	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSameNameSameCardinality()
	{
		DTDNameContent content1 = new DTDNameContent("a");
		DTDNameContent content2 = new DTDNameContent("a");
		
		assertEquals(content1, content2);
	}

	@Test
	public void equalsSameNameDifferentCardinality()
	{
		DTDNameContent content1 = new DTDNameContent("a");
		DTDNameContent content2 = new DTDNameContent("a", DTDCardinality.ONE_OR_MORE);
		
		assertFalse(content1.equals(content2));
	}

	@Test
	public void equalsDifferentNameSameCardinality()
	{
		DTDNameContent content1 = new DTDNameContent("a");
		DTDNameContent content2 = new DTDNameContent("b");
		
		assertFalse(content1.equals(content2));
	}

	@Test
	public void equalsDifferentNameDifferentCardinality()
	{
		DTDNameContent content1 = new DTDNameContent("a");
		DTDNameContent content2 = new DTDNameContent("b", DTDCardinality.ONE_OR_MORE);
		
		assertFalse(content1.equals(content2));
	}
	
	// toString tests ---------------------------------------------------------
	
	@Test
	public void toStringTest()
	{
		DTDNameContent content = new DTDNameContent("a", DTDCardinality.ONE_OR_MORE);
		
		assertEquals("a+", content.toString());
	}
}
