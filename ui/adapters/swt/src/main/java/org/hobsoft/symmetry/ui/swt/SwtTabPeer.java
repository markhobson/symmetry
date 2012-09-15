/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtTabPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Tab;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtTabPeer extends SwtButtonPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtTabPeer(PeerManager peerManager)
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
		
		return new TabItem((TabFolder) parent, SWT.NONE);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Tab component = (Tab) event.getSource();
		String name = event.getPropertyName();
		// Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		TabItem tabItem = (TabItem) getPeerManager().getPeer(component);
		
		if (Tab.COMPONENT_PROPERTY.equals(name))
		{
			tabItem.setControl((Control) getPeerManager().getPeer(newValue));
		}
		else
		{
			super.propertyChange(event);
		}
	}
	
	// SWTButtonPeer methods --------------------------------------------------
	
//	@Override
//	protected void addSelectionListener(SelectionListener listener)
//	{
//		// TODO: add to TabFolder?
//	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setEnabled(Widget widget, boolean enabled)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getText(Widget widget)
	{
		return ((TabItem) widget).getText();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setText(Widget widget, String text)
	{
		((TabItem) widget).setText(text);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setToolTipText(Widget widget, String toolTip)
	{
		((TabItem) widget).setToolTipText(toolTip);
	}
}
