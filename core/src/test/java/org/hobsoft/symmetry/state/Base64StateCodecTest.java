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
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.JdkBeanIntrospector;
import org.hobsoft.symmetry.support.bean.Properties;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Base64StateCodec}.
 * 
 * @author Mark Hobson
 * @see Base64StateCodec
 */
public class Base64StateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Base64StateCodec stateCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		// TODO: use stub component codec
		stateCodec = new Base64StateCodec(null, new JdkBeanIntrospector(), null);
	}

	// tests ------------------------------------------------------------------

	@Test
	public void decodePropertyValueWithBooleanPrimitiveAndNull() throws StateException
	{
		assertEquals(false, decodePropertyValue("booleanPrimitive", null));
	}
	
	@Test
	public void decodePropertyValueWithIntegerPrimitiveAndNull() throws StateException
	{
		assertEquals(-1, decodePropertyValue("integerPrimitive", null));
	}
	
	// private methods --------------------------------------------------------
	
	private Object decodePropertyValue(String propertyName, String encodedPropertyValue) throws StateException
	{
		PropertyDescriptor descriptor = Properties.getDescriptor(FakeBean.class, propertyName);
		
		return stateCodec.decodePropertyValue(new FakeBean(), descriptor, encodedPropertyValue);
	}
}
