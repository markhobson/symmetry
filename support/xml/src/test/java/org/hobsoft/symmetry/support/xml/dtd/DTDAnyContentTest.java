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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>DTDAnyContent</code>.
 * 
 * @author Mark Hobson
 * @see DTDAnyContent
 */
public class DTDAnyContentTest
{
	// fields -----------------------------------------------------------------
	
	private DTDAnyContent content;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		content = DTDAnyContent.INSTANCE;
	}
	
	// validate tests ---------------------------------------------------------
	
	@Test
	public void validateEmpty()
	{
		assertEquals(0, content.validate(0));
	}
	
	@Test
	public void validateSingle()
	{
		assertEquals(1, content.validate(0, "a"));
	}
	
	@Test
	public void validateMultiple()
	{
		assertEquals(3, content.validate(0, "a", "b", "c"));
	}
	
	// toString tests ---------------------------------------------------------
	
	@Test
	public void toStringTest()
	{
		assertEquals("ANY", content.toString());
	}
}
