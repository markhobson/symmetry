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
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>NegationFilter</code>.
 * 
 * @author Mark Hobson
 * @see NegationFilter
 */
public class NegationFilterTest extends AbstractNodeFilterTest
{
	// TODO: resolve ternary vs boolean logic

	// tests ------------------------------------------------------------------
	
	@Test
	public void accept()
	{
		assertSkip(new NegationFilter(ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void reject()
	{
		assertReject(new NegationFilter(ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void skip()
	{
		assertAccept(new NegationFilter(ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void toStringWithNoArgs()
	{
		assertEquals("not mock", new NegationFilter(new MockFilter()).toString());
	}
}
