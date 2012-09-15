/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtButtonPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtButtonPeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
//	// TODO: update for actionListeners
//	private class SelectionListenerAdapter extends ListenerAdapter implements SelectionListener
//	{
//		public SelectionListenerAdapter(ActionListener listener)
//		{
//			super(listener);
//		}
//		
//		public void widgetSelected(SelectionEvent event)
//		{
//			ActionEvent actionEvent = new ActionEvent((Button) getComponent());
//			((ActionListener) getListener()).actionPerformed(actionEvent);
//		}
//		
//		public void widgetDefaultSelected(SelectionEvent event)
//		{
//			widgetSelected(event);
//		}
//	}
	
	// constructors -----------------------------------------------------------
	
	public SwtButtonPeer(PeerManager peerManager)
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
		
		return new org.eclipse.swt.widgets.Button(parent, SWT.PUSH);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Button component = (Button) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		org.eclipse.swt.widgets.Button button = (org.eclipse.swt.widgets.Button) getPeerManager().getPeer(component);
		
		if (Button.ACTION_LISTENERS_PROPERTY.equals(name))
		{
			// TODO: update for actionListeners
//			if (oldValue == null)
//			{
//				addSelectionListener(new SelectionListenerAdapter((ActionListener) newValue));
//			}
//			else if (newValue == null)
//			{
//				removeSelectionListener((SelectionListener) getListener((ActionListener) oldValue));
//			}
		}
		else if (Button.IMAGE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Button.ENABLED_PROPERTY.equals(name))
		{
			setEnabled(button, ((Boolean) newValue).booleanValue());
		}
		else if (Button.MNEMONIC_PROPERTY.equals(name))
		{
			setText(button, SwtUtils.getText(getText(button).replaceAll("&", ""), ((Integer) newValue).intValue()));
		}
		else if (Button.TEXT_PROPERTY.equals(name))
		{
			setText(button, (String) newValue);
		}
		else if (Button.TOOL_TIP_PROPERTY.equals(name))
		{
			setToolTipText(button, (String) newValue);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	// TODO: update for actionListeners
//	protected void addSelectionListener(SelectionListener listener)
//	{
//		((org.eclipse.swt.widgets.Button) getPeer()).addSelectionListener(listener);
//	}
//	
//	protected void removeSelectionListener(SelectionListener listener)
//	{
//		((org.eclipse.swt.widgets.Button) getPeer()).removeSelectionListener(listener);
//	}
	
	protected void setEnabled(Widget widget, boolean enabled)
	{
		((Control) widget).setEnabled(enabled);
	}
	
	protected String getText(Widget widget)
	{
		return ((org.eclipse.swt.widgets.Button) widget).getText();
	}
	
	protected void setText(Widget widget, String text)
	{
		((org.eclipse.swt.widgets.Button) widget).setText(text);
	}
	
	protected void setToolTipText(Widget widget, String toolTip)
	{
		((Control) widget).setToolTipText(toolTip);
	}
}
