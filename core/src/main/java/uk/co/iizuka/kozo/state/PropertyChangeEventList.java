/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/PropertyChangeEventList.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections.keyvalue.MultiKey;

import uk.co.iizuka.kozo.util.lang.ObjectUtils;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PropertyChangeEventList.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class PropertyChangeEventList
{
	// fields -----------------------------------------------------------------
	
	private final Map<MultiKey, PropertyChangeEvent> eventMap;
	
	// constructors -----------------------------------------------------------
	
	public PropertyChangeEventList()
	{
		eventMap = new LinkedHashMap<MultiKey, PropertyChangeEvent>();
	}
	
	// public methods ---------------------------------------------------------
	
	public void addPropertyChangeEvent(PropertyChangeEvent event)
	{
		MultiKey key = new MultiKey(event.getSource(), event.getPropertyName());
		PropertyChangeEvent oldEvent = eventMap.get(key);
		if (oldEvent != null)
		{
			if (!ObjectUtils.equals(oldEvent.getNewValue(), event.getOldValue()))
			{
				throw new IllegalStateException("Inconsistent events for property " + event.getPropertyName() + ": "
					+ ObjectUtils.toString(oldEvent.getNewValue()) + " and "
					+ ObjectUtils.toString(event.getOldValue()));
			}
			
			Object oldValue = oldEvent.getOldValue();
			Object newValue = event.getNewValue();
			
			if (ObjectUtils.equals(oldValue, newValue))
			{
				eventMap.remove(key);
			}
			else
			{
				eventMap.put(key, new PropertyChangeEvent(event.getSource(), event.getPropertyName(), oldValue,
					newValue));
			}
		}
		else
		{
			eventMap.put(key, event);
		}
	}
	
	public PropertyChangeEvent[] getPropertyChangeEvents()
	{
		Collection<PropertyChangeEvent> values = eventMap.values();
		return values.toArray(new PropertyChangeEvent[values.size()]);
	}
}
