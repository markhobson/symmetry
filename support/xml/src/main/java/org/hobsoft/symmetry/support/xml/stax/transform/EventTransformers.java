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

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.EventTransformer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class EventTransformers
{
	// constants --------------------------------------------------------------
	
	private static final EventTransformer IDENTITY_TRANSFORMER = new EventTransformer()
	{
		public XMLEvent transform(XMLEvent event)
		{
			return event;
		}
	};
	
	private static final EventTransformer CHARACTERS_TRANSFORMER = new CharactersEventTransformer();

	// constructors -----------------------------------------------------------
	
	private EventTransformers()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static EventTransformer identity()
	{
		return IDENTITY_TRANSFORMER;
	}
	
	public static EventTransformer compose(final EventTransformer transformer1, final EventTransformer transformer2)
	{
		if (transformer1 == null && transformer2 == null)
		{
			return identity();
		}
		
		if (transformer1 == null)
		{
			return transformer2;
		}
		
		if (transformer2 == null)
		{
			return transformer1;
		}
		
		return new EventTransformer()
		{
			public XMLEvent transform(XMLEvent event)
			{
				return transformer2.transform(transformer1.transform(event));
			}
		};
	}
	
	public static EventTransformer compose(final EventFilter filter, EventTransformer acceptTransformer,
		EventTransformer rejectTransformer)
	{
		if (filter == null)
		{
			throw new NullPointerException("filter cannot be null");
		}
		
		final EventTransformer safeAcceptTransformer = nullSafeTransformer(acceptTransformer);
		final EventTransformer safeRejectTransformer = nullSafeTransformer(rejectTransformer);
		
		return new EventTransformer()
		{
			public XMLEvent transform(XMLEvent event)
			{
				EventTransformer transformer = filter.accept(event) ? safeAcceptTransformer : safeRejectTransformer;
				
				return transformer.transform(event);
			}
		};
	}
	
	public static EventTransformer characters()
	{
		return CHARACTERS_TRANSFORMER;
	}
	
	public static EventTransformer namespace(String targetNamespaceUri)
	{
		return new NamespaceEventTransformer(targetNamespaceUri);
	}
	
	// private methods --------------------------------------------------------
	
	private static EventTransformer nullSafeTransformer(EventTransformer transformer)
	{
		return (transformer != null) ? transformer : identity();
	}
}
