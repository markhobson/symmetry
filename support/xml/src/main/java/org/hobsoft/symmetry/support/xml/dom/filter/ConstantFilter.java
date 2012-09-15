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

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A <code>NodeFilter</code> that always returns the same filter result for all nodes.
 * 
 * @author Mark Hobson
 */
public class ConstantFilter implements NodeFilter
{
	// constants --------------------------------------------------------------
	
	public static final ConstantFilter ACCEPT = new ConstantFilter(FILTER_ACCEPT);
	
	public static final ConstantFilter REJECT = new ConstantFilter(FILTER_REJECT);
	
	public static final ConstantFilter SKIP = new ConstantFilter(FILTER_SKIP);
	
	private static final String[] STRING_VALUES = {
		"accept",
		"reject",
		"skip",
	};
	
	// fields -----------------------------------------------------------------
	
	private final short result;
	
	// constructors -----------------------------------------------------------
	
	protected ConstantFilter(short result)
	{
		if (result != NodeFilter.FILTER_ACCEPT && result != NodeFilter.FILTER_REJECT
			&& result != NodeFilter.FILTER_SKIP)
		{
			throw new IllegalArgumentException("result must be FILTER_ACCEPT, FILTER_REJECT or FILTER_SKIP");
		}
		
		this.result = result;
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		return result;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return STRING_VALUES[result - 1];
	}
}
