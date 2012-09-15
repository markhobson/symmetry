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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code EncodedState}.
 * 
 * @author Mark Hobson
 * @see EncodedState
 */
public class EncodedStateTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void getParameterValueWhenNone()
	{
		EncodedState encodedState = new EncodedState(createParameters("a"));
		
		assertNull(encodedState.getParameterValue("a"));
	}
	
	@Test
	public void getParameterValueWhenSingleValued()
	{
		EncodedState encodedState = new EncodedState(createParameters("a", "x"));
		
		assertEquals("x", encodedState.getParameterValue("a"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getParameterValueWhenMultiValued()
	{
		EncodedState encodedState = new EncodedState(createParameters("a", "x", "y"));
		
		encodedState.getParameterValue("a");
	}
	
	@Test
	public void getParameterValueWhenNotPresent()
	{
		EncodedState encodedState = new EncodedState();
		
		assertNull(encodedState.getParameterValue("a"));
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		String state = createState();
		Map<String, List<Object>> parameters = createParameters();
		
		EncodedState encodedState1 = new EncodedState(state, parameters);
		EncodedState encodedState2 = new EncodedState(state, parameters);
		
		assertEquals(encodedState1.hashCode(), encodedState2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		String state = createState();
		Map<String, List<Object>> parameters = createParameters();
		
		EncodedState encodedState1 = new EncodedState(state, parameters);
		EncodedState encodedState2 = new EncodedState(state, parameters);
		
		assertTrue(encodedState1.equals(encodedState2));
	}
	
	@Test
	public void equalsWhenStateNotEqual()
	{
		Map<String, List<Object>> parameters = createParameters();
		
		EncodedState encodedState1 = new EncodedState("x", parameters);
		EncodedState encodedState2 = new EncodedState("y", parameters);
		
		assertFalse(encodedState1.equals(encodedState2));
	}
	
	@Test
	public void equalsWhenParametersNotEqual()
	{
		String state = createState();
		
		EncodedState encodedState1 = new EncodedState(state, createParameters("a", new Object()));
		EncodedState encodedState2 = new EncodedState(state, createParameters("b", new Object()));
		
		assertFalse(encodedState1.equals(encodedState2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		EncodedState encodedState = createEncodedState();
		
		assertFalse(encodedState.equals(new Object()));
	}
	
	@Test
	public void equalsWithNull()
	{
		EncodedState encodedState = createEncodedState();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(encodedState.equals(null));
		// CHECKSTYLE:ON
	}
	
	@Test
	public void toStringTest()
	{
		EncodedState encodedState = new EncodedState("x", createParameters("a", "b"));
		
		assertEquals("org.hobsoft.symmetry.state.EncodedState[state=x, parameters={a=[b]}]", encodedState.toString());
	}
	
	// private methods --------------------------------------------------------
	
	private static EncodedState createEncodedState()
	{
		return new EncodedState(createState(), createParameters());
	}
	
	private static String createState()
	{
		return "x";
	}
	
	private static Map<String, List<Object>> createParameters()
	{
		Map<String, List<Object>> parameters = new HashMap<String, List<Object>>();
		parameters.put("a", Arrays.asList(new Object(), new Object()));
		parameters.put("b", Arrays.asList(new Object(), new Object()));
		return parameters;
	}
	
	private static Map<String, List<Object>> createParameters(String parameterName, Object... parameterValues)
	{
		return Collections.singletonMap(parameterName, Arrays.asList(parameterValues));
	}
}