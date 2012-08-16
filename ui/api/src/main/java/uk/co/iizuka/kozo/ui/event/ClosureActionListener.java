/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/ClosureActionListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.event;

import uk.co.iizuka.kozo.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ClosureActionListener.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public final class ClosureActionListener extends AbstractClosureEventListener<ActionEvent> implements ActionListener
{
	// constructors -----------------------------------------------------------
	
	public ClosureActionListener(Closure<? super ActionEvent> closure)
	{
		super(closure);
	}
	
	// ActionListener methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		getClosure().apply(event);
	}
}
