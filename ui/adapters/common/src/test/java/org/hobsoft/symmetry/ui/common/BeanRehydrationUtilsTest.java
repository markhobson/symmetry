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
package org.hobsoft.symmetry.ui.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.test.state.StubStateCodec;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code BeanRehydrationUtils}.
 * 
 * @author Mark Hobson
 * @see BeanRehydrationUtils
 */
public class BeanRehydrationUtilsTest
{
	// TODO: centralise duplicated test support methods with AbstractRenderKitTest
	
	// fields -----------------------------------------------------------------

	private FakeBean bean;
	
	private StubStateCodec stateCodec;
	
	private RehydrationContext context;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		bean = new FakeBean();
		
		stateCodec = new StubStateCodec();
		
		context = new RehydrationContext();
		context.set(StateCodec.class, stateCodec);
	}

	// tests ------------------------------------------------------------------

	@Test
	public void setParameterPropertyWithMandatory() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		stateCodec.setEncodedPropertyValue(bean, "name", "x", "x");
		setEncodedState(createEncodedState("bean", "x"));
		
		BeanRehydrationUtils.setParameterProperty(bean, "name", context, false);
		
		assertEquals("x", bean.getName());
	}
	
	@Test
	public void setParameterPropertyWithMandatoryWhenNoValue() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		stateCodec.setEncodedPropertyValue(bean, "name", "y", null);
		bean.setName("x");
		
		BeanRehydrationUtils.setParameterProperty(bean, "name", context, false);
		
		assertEquals("y", bean.getName());
	}
	
	@Test(expected = StateException.class)
	public void setParameterPropertyWithMandatoryWhenMultivalued() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		setEncodedState(createEncodedState("bean", "x", "y"));
		
		BeanRehydrationUtils.setParameterProperty(bean, "name", context, false);
	}
	
	@Test
	public void setParameterPropertyWithMandatoryArray() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		stateCodec.setEncodedPropertyValue(bean, "nickNames", new String[] {"x"}, "x");
		setEncodedState(createEncodedState("bean", "x"));
		
		BeanRehydrationUtils.setParameterProperty(bean, "nickNames", context, false);
		
		assertArrayEquals(new String[] {"x"}, bean.getNickNames());
	}
	
	@Test
	public void setParameterPropertyWithMandatoryArrayWhenNoValue() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		bean.setNickNames(new String[] {"x", "y"});
		
		BeanRehydrationUtils.setParameterProperty(bean, "nickNames", context, false);
		
		assertArrayEquals(new String[0], bean.getNickNames());
	}
	
	@Test
	public void setParameterPropertyWithMandatoryArrayWhenMultivalued() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		stateCodec.setEncodedPropertyValue(bean, "nickNames", new String[] {"x"}, "x");
		stateCodec.setEncodedPropertyValue(bean, "nickNames", new String[] {"y"}, "y");
		setEncodedState(createEncodedState("bean", "x", "y"));
		
		BeanRehydrationUtils.setParameterProperty(bean, "nickNames", context, false);
		
		assertArrayEquals(new String[] {"x", "y"}, bean.getNickNames());
	}
	
	@Test
	public void setParameterPropertyWithMandatoryObject() throws StateException
	{
		Object value = new Object();
		
		stateCodec.setEncodedBean(bean, "bean");
		setEncodedState(createEncodedState("bean", value));
		
		BeanRehydrationUtils.setParameterProperty(bean, "data", context, false);
		
		assertEquals(value, bean.getData());
	}
	
	@Test
	public void setParameterPropertyWithMandatoryObjectWhenNoValue() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		bean.setData(new Object());
		
		BeanRehydrationUtils.setParameterProperty(bean, "data", context, false);
		
		assertNull(bean.getData());
	}
	
	@Test
	public void setParameterPropertyWithOptional() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		stateCodec.setEncodedPropertyValue(bean, "name", "x", "x");
		setEncodedState(createEncodedState("bean", "x"));
		
		BeanRehydrationUtils.setParameterProperty(bean, "name", context, true);
		
		assertEquals("x", bean.getName());
	}
	
	@Test
	public void setParameterPropertyWithOptionalWhenNoValue() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		bean.setName("x");
		
		BeanRehydrationUtils.setParameterProperty(bean, "name", context, true);
		
		assertEquals("x", bean.getName());
	}
	
	@Test(expected = StateException.class)
	public void setParameterPropertyWithOptionalWhenMultivalued() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		setEncodedState(createEncodedState("bean", "x", "y"));
		
		BeanRehydrationUtils.setParameterProperty(bean, "name", context, true);
	}
	
	@Test
	public void setParameterPropertyWithOptionalArray() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		stateCodec.setEncodedPropertyValue(bean, "nickNames", new String[] {"x"}, "x");
		setEncodedState(createEncodedState("bean", "x"));
		
		BeanRehydrationUtils.setParameterProperty(bean, "nickNames", context, true);
		
		assertArrayEquals(new String[] {"x"}, bean.getNickNames());
	}
	
	@Test
	public void setParameterPropertyWithOptionalArrayWhenNoValue() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		bean.setNickNames(new String[] {"x", "y"});

		BeanRehydrationUtils.setParameterProperty(bean, "nickNames", context, true);
		
		assertArrayEquals(new String[] {"x", "y"}, bean.getNickNames());
	}
	
	@Test
	public void setParameterPropertyWithOptionalArrayWhenMultivalued() throws StateException
	{
		stateCodec.setEncodedBean(bean, "bean");
		stateCodec.setEncodedPropertyValue(bean, "nickNames", new String[] {"x"}, "x");
		stateCodec.setEncodedPropertyValue(bean, "nickNames", new String[] {"y"}, "y");
		setEncodedState(createEncodedState("bean", "x", "y"));

		BeanRehydrationUtils.setParameterProperty(bean, "nickNames", context, true);
		
		assertArrayEquals(new String[] {"x", "y"}, bean.getNickNames());
	}
	
	@Test
	public void setParameterPropertyWithOptionalObject() throws StateException
	{
		Object value = new Object();
		
		stateCodec.setEncodedBean(bean, "bean");
		setEncodedState(createEncodedState("bean", value));
		
		BeanRehydrationUtils.setParameterProperty(bean, "data", context, true);
		
		assertEquals(value, bean.getData());
	}
	
	@Test
	public void setParameterPropertyWithOptionalObjectWhenNoValue() throws StateException
	{
		Object value = new Object();
		
		stateCodec.setEncodedBean(bean, "bean");
		bean.setData(value);
		
		BeanRehydrationUtils.setParameterProperty(bean, "data", context, true);
		
		assertEquals(value, bean.getData());
	}
	
	// private methods --------------------------------------------------------

	// TODO: remove the need for this by not duplicating encoded state
	private void setEncodedState(EncodedState encodedState)
	{
		context.setEncodedState(encodedState);
		stateCodec.setEncodedState(encodedState);
	}

	// TODO: remove the need for this by improving EncodedState
	private static EncodedState createEncodedState(String parameterName, Object... parameterValues)
	{
		return new EncodedState(createParameters(parameterName, parameterValues));
	}
	
	private static Map<String, List<Object>> createParameters(String parameterName, Object... parameterValues)
	{
		return Collections.singletonMap(parameterName, Arrays.asList(parameterValues));
	}
}
