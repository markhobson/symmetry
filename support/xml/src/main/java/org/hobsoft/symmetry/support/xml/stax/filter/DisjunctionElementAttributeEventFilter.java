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

import java.util.Iterator;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.Attribute;

/**
 * 
 * 
 * @author Mark Hobson
 */
class DisjunctionElementAttributeEventFilter extends AbstractElementAttributeEventFilter
{
	// constructors -----------------------------------------------------------
	
	public DisjunctionElementAttributeEventFilter(EventFilter attributeFilter)
	{
		super(attributeFilter);
	}

	// AbstractElementAttributeEventFilter methods ----------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean accept(Iterator<Attribute> attributes)
	{
		boolean accept = false;
		
		while (attributes.hasNext() && !accept)
		{
			Attribute attribute = attributes.next();
			
			accept = getDelegate().accept(attribute);
		}
		
		return accept;
	}
}
