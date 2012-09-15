/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtToggleButtonPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.ToggleButton;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtToggleButtonPeer extends SwtButtonPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtToggleButtonPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		Composite parent = SwtUtils.getComposite((Widget) getPeerManager().getPeer(((Component) component)
			.getParent()));
		
		return new org.eclipse.swt.widgets.Button(parent, SWT.TOGGLE);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		ToggleButton component = (ToggleButton) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		org.eclipse.swt.widgets.Button button = (org.eclipse.swt.widgets.Button) getPeerManager().getPeer(component);
		
		if (ToggleButton.SELECTED_PROPERTY.equals(name))
		{
			button.setSelection(((Boolean) newValue).booleanValue());
		}
		else
		{
			super.propertyChange(event);
		}
	}
}
