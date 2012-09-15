/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/WizardEvent.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import org.hobsoft.symmetry.ui.Wizard;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class WizardEvent extends ComponentEvent
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// constructors -----------------------------------------------------------
	
	public WizardEvent(Wizard source)
	{
		super(source);
	}
	
	// EventObject methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wizard getSource()
	{
		return (Wizard) super.getSource();
	}
}
