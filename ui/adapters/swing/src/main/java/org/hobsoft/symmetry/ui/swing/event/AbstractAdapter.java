/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/event/AbstractAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing.event;

import java.lang.ref.WeakReference;

import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractAdapter.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractAdapter
{
	// fields -----------------------------------------------------------------
	
	private WeakReference<Component> componentRef;
	
	// constructors -----------------------------------------------------------
	
	public AbstractAdapter(Component component)
	{
		componentRef = new WeakReference<Component>(component);
	}
	
	// public methods ---------------------------------------------------------
	
	public Component getComponent()
	{
		return componentRef.get();
	}
}
