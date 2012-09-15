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
package org.hobsoft.symmetry.hydrate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code MediaTypes}.
 * 
 * @author Mark Hobson
 * @see MediaTypes
 */
public class MediaTypesTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void getParameterValueWhenNotPresent()
	{
		assertNull(MediaTypes.getParameterValue("a/b", "p"));
	}
	
	@Test
	public void getParameterValueWhenPresent()
	{
		assertEquals("x", MediaTypes.getParameterValue("a/b;p=x", "p"));
	}

	@Test
	public void getParameterValueWhenPresentWithQuotedValue()
	{
		assertEquals("x", MediaTypes.getParameterValue("a/b;p=\"x\"", "p"));
	}
	
	@Test
	public void getParameterValueWhenPresentWithMissingValue()
	{
		assertEquals("", MediaTypes.getParameterValue("a/b;p", "p"));
	}
	
	@Test
	public void getParameterValueWhenPresentWithWhitespace()
	{
		assertEquals("x", MediaTypes.getParameterValue("a/b ; p = x ", "p"));
	}
	
	@Test
	public void getParameterValueWithEmptyMediaType()
	{
		assertNull(MediaTypes.getParameterValue("", "p"));
	}
	
	@Test(expected = NullPointerException.class)
	public void getParameterValueWithNullMediaType()
	{
		assertNull(MediaTypes.getParameterValue(null, "p"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getParameterValueWithEmptyParameterName()
	{
		assertNull(MediaTypes.getParameterValue("a/b", ""));
	}
	
	@Test(expected = NullPointerException.class)
	public void getParameterValueWithNullParameterName()
	{
		assertNull(MediaTypes.getParameterValue("a/b", null));
	}
}
