/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingToolBarPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JToolBar;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Orientation;
import org.hobsoft.symmetry.ui.ToolBar;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingToolBarPeer extends SwingBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingToolBarPeer(PeerManager peerManager)
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
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(true);
		return toolBar;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		ToolBar component = (ToolBar) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JToolBar toolBar = (JToolBar) getPeerManager().getPeer(component);
		
		if (ToolBar.ORIENTATION_PROPERTY.equals(name))
		{
			toolBar.setOrientation(SwingUtils.getOrientation((Orientation) newValue));
		}
		else
		{
			super.propertyChange(event);
		}
	}
}
