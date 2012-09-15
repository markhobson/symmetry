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

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.ToolBar;

/**
 * 
 * 
 * @author Mark Hobson
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
