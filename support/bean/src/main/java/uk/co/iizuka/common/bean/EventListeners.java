/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/EventListeners.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventListenerProxy;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: EventListeners.java 92540 2011-09-20 09:25:58Z mark@IIZUKA.CO.UK $
 */
public final class EventListeners
{
	// constructors -----------------------------------------------------------
	
	private EventListeners()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static EventListener unproxy(EventListener listener)
	{
		EventListener unproxiedListener = listener;
		
		while (unproxiedListener instanceof EventListenerProxy)
		{
			unproxiedListener = ((EventListenerProxy) unproxiedListener).getListener();
		}
		
		return unproxiedListener;
	}
	
	public static EventListener[] unproxy(EventListener[] listeners)
	{
		List<EventListener> unproxiedListeners = new ArrayList<EventListener>();
		
		for (EventListener listener : listeners)
		{
			unproxiedListeners.add(unproxy(listener));
		}
		
		return unproxiedListeners.toArray(new EventListener[unproxiedListeners.size()]);
	}
}
