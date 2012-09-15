/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtToolBarPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.ToolBar;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtToolBarPeer.java 88029 2011-05-13 18:53:16Z mark@IIZUKA.CO.UK $
 */
public class SwtToolBarPeer extends SwtBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtToolBarPeer(PeerManager peerManager)
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
		// TODO: return proper toolbar widget
//		return new org.eclipse.swt.widgets.ToolBar(parent, getSWTStyle());
		return super.createPeer(component);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
//		Object newValue = event.getNewValue();
//		org.eclipse.swt.widgets.ToolBar toolBar = (org.eclipse.swt.widgets.ToolBar) getPeer();
		
		if (ToolBar.ORIENTATION_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else
		{
			super.propertyChange(event);
		}
	}
	
	// private methods --------------------------------------------------------
	
//	private int getSWTStyle()
//	{
//		Orientation orient = ((ToolBar) getComponent()).getOrientation();
//		return SWT.FLAT | SWT.WRAP | SWTUtils.getStyle(orient);
//	}
}
