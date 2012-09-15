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

import java.util.Stack;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
class WellFormedEventFilter extends DelegatingEventFilter
{
	// fields -----------------------------------------------------------------
	
	// TODO: use more efficient data structure
	private final Stack<Boolean> accepts;

	// constructors -----------------------------------------------------------
	
	public WellFormedEventFilter(EventFilter delegate)
	{
		super(delegate);
		
		accepts = new Stack<Boolean>();
	}
	
	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(XMLEvent event)
	{
		boolean accept;
		
		if (event.isStartDocument() || event.isStartElement())
		{
			accept = super.accept(event);
			
			accepts.push(accept);
		}
		else if (event.isEndDocument() || event.isEndElement())
		{
			accept = accepts.pop();
		}
		else
		{
			accept = super.accept(event);
		}
		
		return accept;
	}
}
