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

import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
abstract class AbstractDepthEventFilter extends AbstractHierarchicalEventFilter
{
	// fields -----------------------------------------------------------------
	
	private int depth;
	
	// constructors -----------------------------------------------------------
	
	public AbstractDepthEventFilter()
	{
		depth = 0;
	}

	// AbstractHierarchicalEventFilter methods --------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void start(XMLEvent event)
	{
		depth++;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void end(XMLEvent event)
	{
		depth--;
	}
	
	// protected methods ------------------------------------------------------
	
	protected final int getDepth()
	{
		return depth;
	}
}
