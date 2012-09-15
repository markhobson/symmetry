/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/event/DummyEvent.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyEvent.java 93605 2011-10-06 14:57:04Z mark@IIZUKA.CO.UK $
 */
public class DummyEvent extends ComponentEvent
{
	// constructors -----------------------------------------------------------
	
	public DummyEvent(Component source)
	{
		super(source);
	}
}
