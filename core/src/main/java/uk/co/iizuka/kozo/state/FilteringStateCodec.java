/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/FilteringStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

import java.util.EventListener;
import java.util.EventObject;

import uk.co.iizuka.common.bean.EventSets;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: FilteringStateCodec.java 93015 2011-09-29 08:45:13Z mark@IIZUKA.CO.UK $
 */
public abstract class FilteringStateCodec extends DelegatingStateCodec
{
	// constructors -----------------------------------------------------------
	
	public FilteringStateCodec(StateCodec delegate)
	{
		super(delegate);
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final EncodedState encodeState(State state) throws StateException
	{
		// filter properties
		
		for (PropertyState property : state.getProperties())
		{
			EncodedState encodedState = filterProperty(state, property);
			
			if (encodedState != null)
			{
				return encodedState;
			}
		}
		
		// filter events
		
		for (EventSetState event : state.getEvents())
		{
			EncodedState encodedState = filterEvent(state, event);
			
			if (encodedState != null)
			{
				return encodedState;
			}
		}
		
		return super.encodeState(state);
	}
	
	// protected methods ------------------------------------------------------
	
	protected EncodedState filterProperty(State state, PropertyState property) throws StateException
	{
		return null;
	}
	
	protected EncodedState filterEvent(State state, EventSetState event) throws StateException
	{
		EventObject eventObject = event.getEventObject();
		Object bean = eventObject.getSource();
		EventListener[] listeners = EventSets.getListeners(bean, event.getDescriptor());
		
		for (EventListener listener : listeners)
		{
			EncodedState encodedState = filterEvent(state, listener, eventObject);
			
			if (encodedState != null)
			{
				return encodedState;
			}
		}
		
		return null;
	}
	
	protected EncodedState filterEvent(State state, EventListener listener, EventObject event) throws StateException
	{
		return null;
	}
}
