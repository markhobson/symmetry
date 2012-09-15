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
package org.hobsoft.symmetry.support.xml.stax.filter;

import org.junit.Test;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.accept;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.reject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests <code>DisjunctionEventFilter</code>.
 * 
 * @author Mark Hobson
 * @see DisjunctionEventFilter
 */
public class DisjunctionEventFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithEmpty()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter();
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAccept()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithReject()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(reject());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptAccept()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(accept(), accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptReject()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(accept(), reject());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectAccept()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(reject(), accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectReject()
	{
		DisjunctionEventFilter filter = new DisjunctionEventFilter(reject(), reject());
		
		assertFalse(filter.accept(null));
	}
}
