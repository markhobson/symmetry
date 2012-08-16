/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/OptionEvent.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.event;

import uk.co.iizuka.common.bean.Consumable;
import uk.co.iizuka.kozo.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: OptionEvent.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public final class OptionEvent extends ComponentEvent implements Consumable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// fields -----------------------------------------------------------------
	
	private boolean consumed;
	
	// constructors -----------------------------------------------------------
	
	public OptionEvent(Component source)
	{
		super(source);
		consumed = false;
	}
	
	// Consumable methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isConsumed()
	{
		return consumed;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void consume()
	{
		consumed = true;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		hashCode = (hashCode * 31) + (consumed ? 1 : 0);
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof OptionEvent))
		{
			return false;
		}
		
		OptionEvent event = (OptionEvent) object;
		
		return super.equals(event)
			&& consumed == event.consumed;
	}
}
