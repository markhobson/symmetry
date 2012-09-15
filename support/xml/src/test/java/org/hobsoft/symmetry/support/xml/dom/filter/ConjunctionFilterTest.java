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
 * Tests <code>ConjunctionFilter</code>.
 * 
 * @author Mark Hobson
 * @see ConjunctionFilter
 */
public class ConjunctionFilterTest extends AbstractNodeFilterTest
{
	// TODO: test chaining multiple filters together
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptAccept()
	{
		assertAccept(new ConjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void acceptReject()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void acceptSkip()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.ACCEPT, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void acceptNull()
	{
		assertAccept(new ConjunctionFilter(ConstantFilter.ACCEPT, null), getDocument());
	}
	
	@Test
	public void rejectAccept()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void rejectReject()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void rejectSkip()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void rejectNull()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.REJECT, null), getDocument());
	}
	
	@Test
	public void skipAccept()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.SKIP, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void skipReject()
	{
		assertReject(new ConjunctionFilter(ConstantFilter.SKIP, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void skipSkip()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.SKIP, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void skipNull()
	{
		assertSkip(new ConjunctionFilter(ConstantFilter.SKIP, null), getDocument());
	}
	
	@Test
	public void nullAccept()
	{
		assertAccept(new ConjunctionFilter(null, ConstantFilter.ACCEPT), getDocument());
	}
	
	@Test
	public void nullReject()
	{
		assertReject(new ConjunctionFilter(null, ConstantFilter.REJECT), getDocument());
	}
	
	@Test
	public void nullSkip()
	{
		assertSkip(new ConjunctionFilter(null, ConstantFilter.SKIP), getDocument());
	}
	
	@Test
	public void nullNull()
	{
		assertSkip(new ConjunctionFilter(null, null), getDocument());
	}
	
	@Test
	public void noFilters()
	{
		assertSkip(new ConjunctionFilter(), getDocument());
	}
	
	@Test
	public void toStringNoArgs()
	{
		assertEquals("()", new ConjunctionFilter().toString());
	}
	
	@Test
	public void toStringSingleArgs()
	{
		assertEquals("(mock)", new ConjunctionFilter(new MockFilter()).toString());
	}
	
	@Test
	public void toStringMultipleArgs()
	{
		assertEquals("(mock and mock)", new ConjunctionFilter(new MockFilter(), new MockFilter()).toString());
	}
}
