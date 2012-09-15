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
 * Tests {@code CssStyleBuilder}.
 * 
 * @author Mark Hobson
 * @see CssStyleBuilder
 */
public class CssStyleBuilderTest
{
	// fields -----------------------------------------------------------------
	
	private CssStyleBuilder builder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		builder = new CssStyleBuilder();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void appendIntProperty()
	{
		builder.append(Css.Property.MARGIN, 10, Css.Unit.PX);
		
		assertEquals("margin: 10px", builder.toString());
	}
	
	@Test
	public void appendIntPropertyTwice()
	{
		builder.append(Css.Property.MARGIN, 10, Css.Unit.PX);
		builder.append(Css.Property.PADDING, 20, Css.Unit.EMS);
		
		assertEquals("margin: 10px; padding: 20em", builder.toString());
	}
	
	@Test
	public void appendValueProperty()
	{
		builder.append(Css.Property.VERTICAL_ALIGN, Css.Value.TOP);
		
		assertEquals("vertical-align: top", builder.toString());
	}
	
	@Test
	public void appendWithContent()
	{
		builder = new CssStyleBuilder(new StringBuilder("margin: 10px"));
		
		builder.append(Css.Property.PADDING, 20, Css.Unit.EMS);
		
		assertEquals("margin: 10px; padding: 20em", builder.toString());
	}
}
