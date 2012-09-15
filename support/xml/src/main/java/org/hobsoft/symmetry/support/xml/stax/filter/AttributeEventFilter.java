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
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
class AttributeEventFilter implements EventFilter
{
	// TODO: support attribute value
	
	// fields -----------------------------------------------------------------
	
	private final QName name;

	// constructors -----------------------------------------------------------
	
	public AttributeEventFilter(String localName)
	{
		this(new QName(localName));
	}
	
	public AttributeEventFilter(String namespaceUri, String localName)
	{
		this(new QName(namespaceUri, localName));
	}
	
	public AttributeEventFilter(String namespaceUri, String localName, String prefix)
	{
		this(new QName(namespaceUri, localName, prefix));
	}
	
	public AttributeEventFilter(QName name)
	{
		if (name == null)
		{
			throw new NullPointerException("name cannot be null");
		}
		
		this.name = name;
	}

	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		boolean accept;
		
		int eventType = event.getEventType();
		
		if (eventType == XMLStreamConstants.ATTRIBUTE)
		{
			accept = accept((Attribute) event);
		}
		else
		{
			accept = false;
		}
		
		return accept;
	}
	
	// private methods --------------------------------------------------------
	
	private boolean accept(Attribute attribute)
	{
		return QNameUtils.matches(name, attribute.getName());
	}
}
