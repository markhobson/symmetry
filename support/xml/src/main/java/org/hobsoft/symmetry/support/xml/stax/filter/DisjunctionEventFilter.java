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

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
class DisjunctionEventFilter extends AbstractCompositeEventFilter
{
	// constructors -----------------------------------------------------------
	
	public DisjunctionEventFilter(EventFilter... filters)
	{
		super(filters);
	}
	
	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		boolean accept = false;
		
		EventFilter[] filters = getEventFilters();
		
		for (int i = 0; i < filters.length && !accept; i++)
		{
			accept = filters[i].accept(event);
		}
		
		return accept;
	}
}
