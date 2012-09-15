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

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
class PathEventFilter extends AbstractDepthEventFilter
{
	// fields -----------------------------------------------------------------
	
	private final List<EventFilter> filters;
	
	private final Stack<XMLEvent> events;
	
	// constructors -----------------------------------------------------------
	
	public PathEventFilter(EventFilter... filters)
	{
		this.filters = Arrays.asList(filters);
		
		events = new Stack<XMLEvent>();
	}

	// AbstractDepthEventFilter methods ---------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void start(XMLEvent event)
	{
		// save storing entire path as we're only interested in the start
		if (getDepth() < filters.size())
		{
			events.push(event);
		}
		
		super.start(event);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void end(XMLEvent event)
	{
		super.end(event);
		
		if (getDepth() < filters.size())
		{
			events.pop();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean acceptImpl(XMLEvent event)
	{
		if (events.size() < filters.size())
		{
			return false;
		}
		
		for (int i = 0; i < filters.size(); i++)
		{
			EventFilter filter = filters.get(i);
			XMLEvent parentEvent = events.get(i);
			
			if (!filter.accept(parentEvent))
			{
				return false;
			}
		}
		
		return true;
	}
}
