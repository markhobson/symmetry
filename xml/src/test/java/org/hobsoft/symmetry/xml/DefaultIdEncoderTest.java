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
package org.hobsoft.symmetry.xml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultIdEncoderTest
{
	// fields -----------------------------------------------------------------
	
	private DefaultIdEncoder encoder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		encoder = new DefaultIdEncoder();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encodeOnce()
	{
		assertEquals("0", encoder.apply(new Object()));
	}
	
	@Test
	public void encodeTwice()
	{
		encoder.apply(new Object());
		
		assertEquals("1", encoder.apply(new Object()));
	}
	
	@Test
	public void encodeWithSameObject()
	{
		Object object = new Object();
		encoder.apply(object);
		
		assertEquals("0", encoder.apply(object));
	}
}
