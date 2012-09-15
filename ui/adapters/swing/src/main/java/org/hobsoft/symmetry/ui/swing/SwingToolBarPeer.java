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
