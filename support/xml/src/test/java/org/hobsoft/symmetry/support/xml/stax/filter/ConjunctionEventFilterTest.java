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
 * Tests <code>ConjunctionEventFilter</code>.
 * 
 * @author Mark Hobson
 * @see ConjunctionEventFilter
 */
public class ConjunctionEventFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithEmpty()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter();
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAccept()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithReject()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(reject());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptAccept()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(accept(), accept());
		
		assertTrue(filter.accept(null));
	}
	
	@Test
	public void acceptWithAcceptReject()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(accept(), reject());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectAccept()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(reject(), accept());
		
		assertFalse(filter.accept(null));
	}
	
	@Test
	public void acceptWithRejectReject()
	{
		ConjunctionEventFilter filter = new ConjunctionEventFilter(reject(), reject());
		
		assertFalse(filter.accept(null));
	}
}
