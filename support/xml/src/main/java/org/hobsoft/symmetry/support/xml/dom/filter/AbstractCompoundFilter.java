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

import org.w3c.dom.traversal.NodeFilter;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractCompoundFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	private final NodeFilter[] filters;
	
	// constructors -----------------------------------------------------------
	
	public AbstractCompoundFilter(NodeFilter... filters)
	{
		this.filters = filters;
	}
	
	// public methods ---------------------------------------------------------
	
	public NodeFilter[] getNodeFilters()
	{
		// NOTE: this returns the internal array, and thus can be modified
		
		return filters;
	}
	
	// protected methods ------------------------------------------------------
	
	protected void join(StringBuilder builder, String separator)
	{
		for (int i = 0; i < filters.length; i++)
		{
			if (i > 0)
			{
				builder.append(separator);
			}
			
			builder.append(filters[i]);
		}
	}
}
