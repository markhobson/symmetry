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
package org.hobsoft.symmetry.ui.functor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code BooleanFunctions}.
 * 
 * @author Mark Hobson
 * @see BooleanFunctions
 */
public class BooleanFunctionsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void trueFalseWithTrue()
	{
		assertEquals("True", BooleanFunctions.trueFalse().apply(Boolean.TRUE));
	}
	
	@Test
	public void trueFalseWithFalse()
	{
		assertEquals("False", BooleanFunctions.trueFalse().apply(Boolean.FALSE));
	}
	
	@Test
	public void trueFalseWithNull()
	{
		assertNull(BooleanFunctions.trueFalse().apply(null));
	}
	
	@Test
	public void yesNoWithTrue()
	{
		assertEquals("Yes", BooleanFunctions.yesNo().apply(Boolean.TRUE));
	}
	
	@Test
	public void yesNoWithFalse()
	{
		assertEquals("No", BooleanFunctions.yesNo().apply(Boolean.FALSE));
	}
	
	@Test
	public void yesNoWithNull()
	{
		assertNull(BooleanFunctions.yesNo().apply(null));
	}
	
	@Test
	public void onOffWithTrue()
	{
		assertEquals("On", BooleanFunctions.onOff().apply(Boolean.TRUE));
	}
	
	@Test
	public void onOffWithFalse()
	{
		assertEquals("Off", BooleanFunctions.onOff().apply(Boolean.FALSE));
	}
	
	@Test
	public void onOffWithNull()
	{
		assertNull(BooleanFunctions.onOff().apply(null));
	}
	
	@Test
	public void oneZeroWithTrue()
	{
		assertEquals("1", BooleanFunctions.oneZero().apply(Boolean.TRUE));
	}
	
	@Test
	public void oneZeroWithFalse()
	{
		assertEquals("0", BooleanFunctions.oneZero().apply(Boolean.FALSE));
	}
	
	@Test
	public void oneZeroWithNull()
	{
		assertNull(BooleanFunctions.oneZero().apply(null));
	}
}
