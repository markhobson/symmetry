/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/ActionEvent.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.event;

import uk.co.iizuka.kozo.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ActionEvent.java 93605 2011-10-06 14:57:04Z mark@IIZUKA.CO.UK $
 */
public final class ActionEvent extends ComponentEvent
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// constructors -----------------------------------------------------------
	
	public ActionEvent(Component source)
	{
		super(source);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		// overriden to align with equals()
		
		return super.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		return (object instanceof ActionEvent) && super.equals(object);
	}
}
