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

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Tests <code>EmptyCharacterDataFilter</code>.
 * 
 * @author Mark Hobson
 * @see EmptyCharacterDataFilter
 */
public class EmptyCharacterDataFilterTest extends AbstractNodeFilterTest
{
	// fields -----------------------------------------------------------------
	
	private NodeFilter filter;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		filter = new EmptyCharacterDataFilter();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void emptyTextNode()
	{
		assertAccept(filter, getDocument().createTextNode(""));
	}
	
	@Test
	public void nonEmptyTextNode()
	{
		assertSkip(filter, getDocument().createTextNode("a"));
	}
	
	@Test
	public void emptyCommentNode()
	{
		assertAccept(filter, getDocument().createComment(""));
	}
	
	@Test
	public void nonEmptyCommentNode()
	{
		assertSkip(filter, getDocument().createComment("a"));
	}
	
	@Test
	public void emptyCDATASectionNode()
	{
		assertAccept(filter, getDocument().createCDATASection(""));
	}
	
	@Test
	public void nonEmptyCDATASectionNode()
	{
		assertSkip(filter, getDocument().createCDATASection("a"));
	}
}
