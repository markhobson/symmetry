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

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class EventFilters
{
	// constants --------------------------------------------------------------
	
	private static final EventFilter ACCEPT_FILTER = new EventFilter()
	{
		public boolean accept(XMLEvent event)
		{
			return true;
		}
	};

	private static final EventFilter REJECT_FILTER = new EventFilter()
	{
		public boolean accept(XMLEvent event)
		{
			return false;
		}
	};
	
	// constructors -----------------------------------------------------------
	
	private EventFilters()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static EventFilter accept()
	{
		return ACCEPT_FILTER;
	}
	
	public static EventFilter reject()
	{
		return REJECT_FILTER;
	}
	
	public static EventFilter not(final EventFilter filter)
	{
		return new EventFilter()
		{
			public boolean accept(XMLEvent event)
			{
				return !filter.accept(event);
			}
		};
	}
	
	public static EventFilter and(EventFilter... filters)
	{
		return new ConjunctionEventFilter(filters);
	}
	
	public static EventFilter or(EventFilter... filters)
	{
		return new DisjunctionEventFilter(filters);
	}
	
	public static EventFilter type(int... eventTypes)
	{
		return new TypeEventFilter(eventTypes);
	}
	
	public static EventFilter startElement(String localName)
	{
		return startElement(new QName(localName));
	}
	
	public static EventFilter startElement(String namespaceUri, String localName)
	{
		return startElement(new QName(namespaceUri, localName));
	}
	
	public static EventFilter startElement(String namespaceUri, String localName, String prefix)
	{
		return startElement(new QName(namespaceUri, localName, prefix));
	}
	
	public static EventFilter startElement(final QName name)
	{
		return new EventFilter()
		{
			public boolean accept(XMLEvent event)
			{
				return event.isStartElement() && QNameUtils.matchesIgnoreCase(name, event.asStartElement().getName());
			}
		};
	}
	
	public static EventFilter endElement(String localName)
	{
		return endElement(new QName(localName));
	}
	
	public static EventFilter endElement(String namespaceUri, String localName)
	{
		return endElement(new QName(namespaceUri, localName));
	}
	
	public static EventFilter endElement(String namespaceUri, String localName, String prefix)
	{
		return endElement(new QName(namespaceUri, localName, prefix));
	}
	
	public static EventFilter endElement(final QName name)
	{
		return new EventFilter()
		{
			public boolean accept(XMLEvent event)
			{
				return event.isEndElement() && QNameUtils.matchesIgnoreCase(name, event.asEndElement().getName());
			}
		};
	}
	
	public static EventFilter element(String localName)
	{
		return element(new QName(localName));
	}
	
	public static EventFilter element(String namespaceUri, String localName)
	{
		return element(new QName(namespaceUri, localName));
	}
	
	public static EventFilter element(String namespaceUri, String localName, String prefix)
	{
		return element(new QName(namespaceUri, localName, prefix));
	}
	
	public static EventFilter element(QName name)
	{
		return or(startElement(name), endElement(name));
	}
	
	public static EventFilter attribute(String localName)
	{
		return attribute(new QName(localName));
	}
	
	public static EventFilter attribute(String namespaceUri, String localName)
	{
		return attribute(new QName(namespaceUri, localName));
	}
	
	public static EventFilter attribute(String namespaceUri, String localName, String prefix)
	{
		return attribute(new QName(namespaceUri, localName, prefix));
	}
	
	public static EventFilter attribute(QName name)
	{
		return new AttributeEventFilter(name);
	}
	
	public static EventFilter anyAttribute(EventFilter attributeFilter)
	{
		return new DisjunctionElementAttributeEventFilter(attributeFilter);
	}
	
	public static EventFilter allAttributes(EventFilter attributeFilter)
	{
		return new ConjunctionElementAttributeEventFilter(attributeFilter);
	}
	
	public static EventFilter wellFormed(EventFilter filter)
	{
		return new WellFormedEventFilter(filter);
	}
	
	public static EventFilter depthEq(final int depth)
	{
		return new AbstractDepthEventFilter()
		{
			@Override
			protected boolean acceptImpl(XMLEvent event)
			{
				return (getDepth() == depth);
			}
		};
	}
	
	public static EventFilter depthGt(final int depth)
	{
		return new AbstractDepthEventFilter()
		{
			@Override
			protected boolean acceptImpl(XMLEvent event)
			{
				return (getDepth() > depth);
			}
		};
	}
	
	public static EventFilter depthGe(final int depth)
	{
		return new AbstractDepthEventFilter()
		{
			@Override
			protected boolean acceptImpl(XMLEvent event)
			{
				return (getDepth() >= depth);
			}
		};
	}
	
	public static EventFilter path(EventFilter... filters)
	{
		return new PathEventFilter(filters);
	}
}
