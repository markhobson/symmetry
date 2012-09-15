/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/transform/StubEventTransformer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: StubEventTransformer.java 88597 2011-05-27 11:01:49Z mark@IIZUKA.CO.UK $
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
