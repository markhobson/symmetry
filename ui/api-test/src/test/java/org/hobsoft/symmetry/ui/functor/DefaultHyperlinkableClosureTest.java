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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code DefaultHyperlinkableClosure}.
 * 
 * @author Mark Hobson
 * @see DefaultHyperlinkableClosure
 */
public class DefaultHyperlinkableClosureTest
{
	// TODO: test method and asynchronous properties
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		assertEquals("a", new DefaultHyperlinkableClosure<Void>("a").toHyperlink(null));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructWithNull()
	{
		new DefaultHyperlinkableClosure<Void>(null);
	}
	
	@Test
	public void toHyperlinkWithNull()
	{
		assertEquals("a", new DefaultHyperlinkableClosure<Void>("a").toHyperlink(null));
	}
	
	@Test
	public void toHyperlinkWithObject()
	{
		assertEquals("a", new DefaultHyperlinkableClosure<Object>("a").toHyperlink(new Object()));
	}
	
	@Test
	public void toHyperlinkWithPropertyToken()
	{
		StubBean bean = new StubBean();
		bean.setName("a");
		
		assertEquals("a", new DefaultHyperlinkableClosure<Object>("{name}").toHyperlink(bean));
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		assertEquals(new DefaultHyperlinkableClosure<Void>("a").hashCode(),
			new DefaultHyperlinkableClosure<Void>("a").hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		assertTrue(new DefaultHyperlinkableClosure<Void>("a").equals(new DefaultHyperlinkableClosure<Void>("a")));
	}
	
	@Test
	public void equalsWhenNotEqual()
	{
		assertFalse(new DefaultHyperlinkableClosure<Void>("a").equals(new DefaultHyperlinkableClosure<Void>("b")));
	}
	
	@Test
	public void toStringTest()
	{
		String expected = "org.hobsoft.symmetry.ui.functor.DefaultHyperlinkableClosure[hyperlink=a, method=GET, "
			+ "asynchronous=false]";
		
		assertEquals(expected, new DefaultHyperlinkableClosure<Void>("a").toString());
	}
}
