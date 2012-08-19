/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/EventTransformer.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax;

import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: EventTransformer.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public interface EventTransformer
{
	XMLEvent transform(XMLEvent event);
}
