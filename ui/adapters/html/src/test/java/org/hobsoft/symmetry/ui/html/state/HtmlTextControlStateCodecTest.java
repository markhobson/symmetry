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
package org.hobsoft.symmetry.ui.html.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.state.NullStateCodec;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.support.bean.Properties;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code HtmlTextControlStateCodec}.
 * 
 * @author Mark Hobson
 * @see HtmlTextControlStateCodec
 */
public class HtmlTextControlStateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Object bean;
	
	private PropertyDescriptor descriptor;
	
	private HtmlTextControlStateCodec stateCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		bean = new FakeBean();
		descriptor = Properties.getDescriptor(FakeBean.class, "string");
		
		stateCodec = new HtmlTextControlStateCodec(new NullStateCodec(), descriptor);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encodePropertyValueWithString() throws StateException
	{
		assertEquals("a", encodePropertyValue("a"));
	}
	
	@Test
	public void encodePropertyValueWithEmptyString() throws StateException
	{
		assertEquals("", encodePropertyValue(""));
	}
	
	@Test
	public void decodePropertyValueWithString() throws StateException
	{
		assertEquals("a", decodePropertyValue("a"));
	}
	
	@Test
	public void decodePropertyValueWithEmptyString() throws StateException
	{
		assertEquals("", decodePropertyValue(""));
	}
	
	// private methods --------------------------------------------------------
	
	private String encodePropertyValue(Object value) throws StateException
	{
		return stateCodec.encodePropertyValue(new PropertyState(bean, descriptor, value));
	}
	
	private Object decodePropertyValue(String encodedValue) throws StateException
	{
		return stateCodec.decodePropertyValue(bean, descriptor, encodedValue);
	}
}
