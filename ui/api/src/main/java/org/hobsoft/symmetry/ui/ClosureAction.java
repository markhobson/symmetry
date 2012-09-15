/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/ClosureAction.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ClosureEventListener;
import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * An action that executes a closure when performed.
 * 
 * @author Mark Hobson
 * @version $Id: ClosureAction.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class ClosureAction extends DefaultAction implements ClosureEventListener<ActionEvent>
{
	// constants --------------------------------------------------------------
	
	public static final String CLOSURE_PROPERTY = "closure";

	// fields -----------------------------------------------------------------
	
	private Closure<? super ActionEvent> closure;
	
	// constructors -----------------------------------------------------------
	
	public ClosureAction()
	{
		super();
	}
	
	public ClosureAction(String name)
	{
		super(name);
	}
	
	public ClosureAction(String name, Closure<? super ActionEvent> closure)
	{
		this(name);
		
		setClosure(closure);
	}
	
	// ActionListener methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		closure.apply(event);
	}
	
	// ClosureEventListener methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Closure<? super ActionEvent> getClosure()
	{
		return closure;
	}
	
	// public methods ---------------------------------------------------------
	
	public void setClosure(Closure<? super ActionEvent> closure)
	{
		Closure<? super ActionEvent> oldClosure = this.closure;
		this.closure = closure;
		firePropertyChange(CLOSURE_PROPERTY, oldClosure, closure);
	}
}
