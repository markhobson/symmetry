/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/DummyComponent.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * Dummy component for tests.
 * 
 * @author Mark Hobson
 * @version $Id: DummyComponent.java 95444 2011-11-22 17:25:00Z mark@IIZUKA.CO.UK $
 */
public class DummyComponent extends Component
{
	// constants --------------------------------------------------------------
	
	public static final String STATE_PROPERTY = "state";

	// fields -----------------------------------------------------------------
	
	private boolean state;
	
	// constructors -----------------------------------------------------------
	
	public DummyComponent()
	{
		state = false;
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, DummyComponent.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public boolean getState()
	{
		return state;
	}
	
	public void setState(boolean state)
	{
		this.state = state;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "DummyComponent";
	}
}
