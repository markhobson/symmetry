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
package org.hobsoft.symmetry.support.xml.stax.transform;

import java.util.Arrays;
import java.util.Iterator;

import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.EventTransformer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StubEventTransformer implements EventTransformer
{
	// fields -----------------------------------------------------------------
	
	private final Iterator<XMLEvent> events;
	
	// constructors -----------------------------------------------------------
	
	public StubEventTransformer(XMLEvent... events)
	{
		this.events = Arrays.asList(events).iterator();
	}
	
	// EventTransformer methods -----------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public XMLEvent transform(XMLEvent event)
	{
		return events.next();
	}
}
