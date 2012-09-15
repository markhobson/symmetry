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
package org.hobsoft.symmetry.css;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code CssClassBuilder}.
 * 
 * @author Mark Hobson
 * @see CssClassBuilder
 */
public class CssClassBuilderTest
{
	// fields -----------------------------------------------------------------
	
	private CssClassBuilder builder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		builder = new CssClassBuilder();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void appendOnce()
	{
		builder.append("a");
		
		assertEquals("a", builder.toString());
	}
	
	@Test
	public void appendTwice()
	{
		builder.append("a");
		builder.append("b");
		
		assertEquals("a b", builder.toString());
	}
	
	@Test
	public void appendAfterStringBuilderConstruction()
	{
		builder = new CssClassBuilder(new StringBuilder("a"));
		
		builder.append("b");
		
		assertEquals("a b", builder.toString());
	}
	
	@Test
	public void appendAfterStringConstruction()
	{
		builder = new CssClassBuilder("a");
		
		builder.append("b");
		
		assertEquals("a b", builder.toString());
	}
}
