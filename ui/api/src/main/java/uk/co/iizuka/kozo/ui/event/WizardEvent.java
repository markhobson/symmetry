/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/WizardEvent.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.event;

import uk.co.iizuka.kozo.ui.Wizard;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: WizardEvent.java 88174 2011-05-18 09:26:41Z mark@IIZUKA.CO.UK $
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
