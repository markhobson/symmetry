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
 * Tests <code>DTDContentContainer</code>.
 * 
 * @author Mark Hobson
 * @see DTDContentContainer
 */
public class DTDContentContainerTest
{
	// TODO: test other methods

	// constructor tests ------------------------------------------------------
	
	@Test
	public void constructorDefault()
	{
		DTDContentContainer content = new MockDTDContentContainer();
		
		assertEquals(DTDCardinality.ONCE, content.getCardinality());
	}
	
	@Test
	public void constructorCardinality()
	{
		DTDContentContainer content = new MockDTDContentContainer(DTDCardinality.ONE_OR_MORE);
		
		assertEquals(DTDCardinality.ONE_OR_MORE, content.getCardinality());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorCardinalityNull()
	{
		try
		{
			new MockDTDContentContainer(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("cardinality: null", exception.getMessage());
			
			throw exception;
		}
	}
	
	// hashCode tests ---------------------------------------------------------
	
	@Test
	public void hashCodeSameCardinalitySameChildren()
	{
		DTDContent child = new MockDTDContent();
		
		DTDContentContainer container1 = new MockDTDContentContainer();
		container1.addChild(child);
		
		DTDContentContainer container2 = new MockDTDContentContainer();
		container2.addChild(child);
		
		assertEquals(container1.hashCode(), container2.hashCode());
	}
	
	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSameCardinalitySameChildren()
	{
		DTDContent child = new MockDTDContent();
		
		DTDContentContainer container1 = new MockDTDContentContainer();
		container1.addChild(child);
		
		DTDContentContainer container2 = new MockDTDContentContainer();
		container2.addChild(child);
		
		assertEquals(container1, container2);
	}
	
	@Test
	public void equalsDifferentCardinalitySameChildren()
	{
		DTDContent child = new MockDTDContent();
		
		DTDContentContainer container1 = new MockDTDContentContainer(DTDCardinality.ONE_OR_MORE);
		container1.addChild(child);
		
		DTDContentContainer container2 = new MockDTDContentContainer();
		container2.addChild(child);
		
		assertFalse(container1.equals(container2));
	}
	
	@Test
	public void equalsSameCardinalityDifferentChildren()
	{
		DTDContent child1 = new MockDTDContent();
		DTDContent child2 = new MockDTDContent();
		
		DTDContentContainer container1 = new MockDTDContentContainer();
		container1.addChild(child1);
		
		DTDContentContainer container2 = new MockDTDContentContainer();
		container2.addChild(child2);
		
		assertFalse(container1.equals(container2));
	}
	
	@Test
	public void equalsDifferentCardinalityDifferentChildren()
	{
		DTDContent child1 = new MockDTDContent();
		DTDContent child2 = new MockDTDContent();
		
		DTDContentContainer container1 = new MockDTDContentContainer(DTDCardinality.ONE_OR_MORE);
		container1.addChild(child1);
		
		DTDContentContainer container2 = new MockDTDContentContainer();
		container2.addChild(child2);
		
		assertFalse(container1.equals(container2));
	}
}
