/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
