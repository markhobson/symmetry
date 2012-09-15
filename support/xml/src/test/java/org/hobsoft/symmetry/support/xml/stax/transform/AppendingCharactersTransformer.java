/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/transform/AppendingCharactersTransformer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.transform;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.EventTransformer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AppendingCharactersTransformer.java 88598 2011-05-27 11:18:52Z mark@IIZUKA.CO.UK $
 */
public class AppendingCharactersTransformer implements EventTransformer
{
	// fields -----------------------------------------------------------------
	
	private final XMLEventFactory eventFactory;
	
	private final String appendContent;
	
	// constructors -----------------------------------------------------------
	
	public AppendingCharactersTransformer(String appendContent)
	{
		eventFactory = XMLEventFactory.newInstance();
		
		this.appendContent = appendContent;
	}

	// EventTransformer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public XMLEvent transform(XMLEvent event)
	{
		String content = event.asCharacters().getData();
		
		return eventFactory.createCharacters(content + appendContent);
	}
}
