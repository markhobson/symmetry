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

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.Map;

import org.hobsoft.symmetry.ui.FakeEnum;
import org.hobsoft.symmetry.ui.StubBean;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code Functions}.
 * 
 * @author Mark Hobson
 * @see Functions
 */
public class FunctionsTest
{
	// TODO: test compose
	// TODO: test tableValue
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void identity()
	{
		assertEquals("a", Functions.identity().apply("a"));
	}
	
	@Test
	public void identityWithNullInput()
	{
		assertNull(Functions.identity().apply(null));
	}
	
	@Test
	public void constant()
	{
		assertEquals("a", Functions.constant("a").apply("b"));
	}
	
	@Test
	public void constantWithNullInput()
	{
		assertEquals("a", Functions.constant("a").apply(null));
	}
	
	@Test
	public void constantWithNullValue()
	{
		assertNull(Functions.constant(null).apply("a"));
	}
	
	@Test
	public void notNullWithObject()
	{
		assertTrue(Functions.notNull().apply("a"));
	}
	
	@Test
	public void notNullWithNull()
	{
		assertFalse(Functions.notNull().apply(null));
	}
	
	@Test
	public void chainWhenFirstValueIsNonNull()
	{
		assertEquals("a", Functions.chain(Functions.constant("a"), Functions.constant("b")).apply("c"));
	}
	
	@Test
	public void chainWhenFirstValueIsNull()
	{
		assertEquals("b", Functions.chain(Functions.constant((String) null), Functions.constant("b")).apply("c"));
	}
	
	@Test
	public void chainWhenFirstValueIsNullAndSecondValueIsNull()
	{
		assertNull(Functions.chain(Functions.constant((String) null), Functions.constant((String) null)).apply("c"));
	}
	
	@Test
	public void cast()
	{
		Function<Object, String> cast = Functions.cast(String.class);
		
		assertEquals("a", cast.apply("a"));
	}
	
	@Test(expected = ClassCastException.class)
	public void castWhenNotAssignable()
	{
		Function<Object, String> cast = Functions.cast(String.class);
		
		cast.apply(123);
	}
	
	@Test
	public void toStringFunction()
	{
		assertEquals("1", Functions.toStringFunction().apply(1));
	}
	
	@Test
	public void toStringFunctionWithNull()
	{
		assertNull(Functions.toStringFunction().apply(null));
	}
	
	@Test
	public void emptyStringToNullWithString()
	{
		assertEquals("a", Functions.emptyStringToNull().apply("a"));
	}
	
	@Test
	public void emptyStringToNullWithEmptyString()
	{
		assertNull(Functions.emptyStringToNull().apply(""));
	}
	
	@Test
	public void emptyStringToNullWithNull()
	{
		assertNull(Functions.emptyStringToNull().apply(null));
	}
	
	@Test
	public void parseInteger()
	{
		assertEquals(Integer.valueOf(1), Functions.parseInteger().apply("1"));
	}
	
	@Test(expected = NumberFormatException.class)
	public void parseIntegerWhenInvalid()
	{
		Functions.parseInteger().apply("x");
	}
	
	@Test(expected = NumberFormatException.class)
	public void parseIntegerWhenEmpty()
	{
		Functions.parseInteger().apply("");
	}
	
	@Test
	public void parseIntegerWhenNull()
	{
		assertNull(Functions.parseInteger().apply(null));
	}
	
	@Test
	public void forMapEntry()
	{
		assertEquals(Integer.valueOf(1), Functions.forMapEntry("a", 1).apply("a"));
	}
	
	@Test
	public void forMap()
	{
		Map<String, Integer> map = Collections.singletonMap("a", 1);
		
		assertEquals(Integer.valueOf(1), Functions.forMap(map).apply("a"));
	}
	
	@Test
	public void propertyNewValue()
	{
		Object source = new Object();
		Object oldValue = new Object();
		Object newValue = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(source, "a", oldValue, newValue);
		
		assertEquals(newValue, Functions.propertyNewValue().apply(event));
	}
	
	@Test
	public void enumTitleCaseWithLetter()
	{
		assertEquals("A", Functions.<FakeEnum>enumTitleCase().apply(FakeEnum.A));
	}
	
	@Test
	public void enumTitleCaseWithWord()
	{
		assertEquals("Word", Functions.<FakeEnum>enumTitleCase().apply(FakeEnum.WORD));
	}
	
	@Test
	public void enumTitleCaseWithMultipleWords()
	{
		assertEquals("Multiple Words", Functions.<FakeEnum>enumTitleCase().apply(FakeEnum.MULTIPLE_WORDS));
	}
	
	@Test
	public void enumTitleCaseWithNull()
	{
		assertNull(Functions.<FakeEnum>enumTitleCase().apply(null));
	}
	
	@Test
	public void enumCamelCaseWithLetter()
	{
		assertEquals("a", Functions.<FakeEnum>enumCamelCase().apply(FakeEnum.A));
	}
	
	@Test
	public void enumCamelCaseWithWord()
	{
		assertEquals("word", Functions.<FakeEnum>enumCamelCase().apply(FakeEnum.WORD));
	}
	
	@Test
	public void enumCamelCaseWithMultipleWords()
	{
		assertEquals("multipleWords", Functions.<FakeEnum>enumCamelCase().apply(FakeEnum.MULTIPLE_WORDS));
	}
	
	@Test
	public void enumCamelCaseWithNull()
	{
		assertNull(Functions.<FakeEnum>enumCamelCase().apply(null));
	}
	
	@Test
	public void evaluate()
	{
		StubBean bean = new StubBean();
		bean.setName("a");
		
		assertEquals("a", Functions.evaluate("{name}").apply(bean));
	}
}
