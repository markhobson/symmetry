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
import org.w3c.dom.traversal.NodeFilter;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>ConstantFilter</code>.
 * 
 * @author Mark Hobson
 * @see ConstantFilter
 */
public class ConstantFilterTest extends AbstractNodeFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void accept()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_ACCEPT);
		assertAccept(filter, getDocument());
	}
	
	@Test
	public void reject()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_REJECT);
		assertReject(filter, getDocument());
	}
	
	@Test
	public void skip()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_SKIP);
		assertSkip(filter, getDocument());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidResult()
	{
		try
		{
			new ConstantFilter((short) 123);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals(exception.getMessage(), "result must be FILTER_ACCEPT, FILTER_REJECT or FILTER_SKIP");
			
			throw exception;
		}
	}
	
	@Test
	public void toStringWithAccept()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_ACCEPT);
		assertEquals("accept", filter.toString());
	}
	
	@Test
	public void toStringWithReject()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_REJECT);
		assertEquals("reject", filter.toString());
	}
	
	@Test
	public void toStringWithSkip()
	{
		NodeFilter filter = new ConstantFilter(NodeFilter.FILTER_SKIP);
		assertEquals("skip", filter.toString());
	}
}
