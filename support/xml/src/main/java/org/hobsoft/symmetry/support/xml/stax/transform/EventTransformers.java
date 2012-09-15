/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/transform/EventTransformers.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.transform;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.EventTransformer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: EventTransformers.java 88599 2011-05-27 11:54:07Z mark@IIZUKA.CO.UK $
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
