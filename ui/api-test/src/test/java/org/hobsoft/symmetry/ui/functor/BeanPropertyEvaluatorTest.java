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

import org.hobsoft.symmetry.ui.StubBean;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code BeanPropertyEvaluator}.
 * 
 * @author Mark Hobson
 * @see BeanPropertyEvaluator
 */
public class BeanPropertyEvaluatorTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void evaluateWithNullString()
	{
		assertNull(BeanPropertyEvaluator.evaluate(null, createBean()));
	}
	
	@Test
	public void evaluateWithNullBean()
	{
		assertEquals("a", BeanPropertyEvaluator.evaluate("a", null));
	}
	
	@Test
	public void evaluateWithPropertyNameToken()
	{
		assertEquals("a", BeanPropertyEvaluator.evaluate("{name}", createBean("a")));
	}
	
	@Test
	public void evaluateWithPropertyNameTokenWhenNull()
	{
		assertEquals("null", BeanPropertyEvaluator.evaluate("{name}", createBean(null)));
	}
	
	@Test
	public void evaluateWithPropertyNameTokenWhenNotFound()
	{
		assertEquals("{a}", BeanPropertyEvaluator.evaluate("{a}", createBean()));
	}
	
	@Test
	public void evaluateWithPropertyNameTokenAndPrecedingText()
	{
		assertEquals("ab", BeanPropertyEvaluator.evaluate("a{name}", createBean("b")));
	}
	
	@Test
	public void evaluateWithPropertyNameTokenAndFollowingText()
	{
		assertEquals("ab", BeanPropertyEvaluator.evaluate("{name}b", createBean("a")));
	}
	
	@Test
	public void evaluateWithPropertyNameTokenAndNullBean()
	{
		assertEquals("{name}", BeanPropertyEvaluator.evaluate("{name}", null));
	}
	
	@Test
	public void evaluateWithPropertyPathToken()
	{
		assertEquals("b", BeanPropertyEvaluator.evaluate("{partner.name}", createBean("a", createBean("b"))));
	}
	
	@Test
	public void evaluateWithPropertyPathTokenWhenNull()
	{
		assertEquals("null", BeanPropertyEvaluator.evaluate("{partner.name}", createBean("a", createBean(null))));
	}
	
	@Test
	public void evaluateWithPropertyPathTokenWhenPrecedingParticleNull()
	{
		assertEquals("{partner.name}", BeanPropertyEvaluator.evaluate("{partner.name}", createBean("a", null)));
	}
	
	@Test
	public void evaluateWithPropertyPathTokenWhenPrecedingParticleNotFound()
	{
		assertEquals("{a.name}", BeanPropertyEvaluator.evaluate("{a.name}", createBean()));
	}
	
	@Test
	public void evaluateWithPropertyPathTokenWhenLastParticleNotFound()
	{
		assertEquals("{partner.a}", BeanPropertyEvaluator.evaluate("{partner.a}", createBean(null, createBean())));
	}
	
	@Test
	public void evaluateWithDeepPropertyPathToken()
	{
		StubBean bean = createBean("a", createBean("b", createBean("c")));
		
		assertEquals("c", BeanPropertyEvaluator.evaluate("{partner.partner.name}", bean));
	}
	
	// private methods --------------------------------------------------------
	
	private static StubBean createBean()
	{
		return new StubBean();
	}
	
	private static StubBean createBean(String name)
	{
		StubBean bean = createBean();
		bean.setName(name);
		return bean;
	}
	
	private static StubBean createBean(String name, StubBean partner)
	{
		StubBean bean = createBean(name);
		bean.setPartner(partner);
		return bean;
	}
}
