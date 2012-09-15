/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/DefaultStateSession.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyChangeEvent;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultStateSession.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class DefaultStateSession implements StateSession
{
	// TODO: remove
	
	// StateSession methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRoot()
	{
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getState()
	{
		return new State();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close()
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		// no-op
	}
}
