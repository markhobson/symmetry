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

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.GroupBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingGroupBoxPeer extends SwingBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingGroupBoxPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerFactory methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		JComponent peer = (JComponent) super.createPeer(component);
		peer.setBorder(BorderFactory.createTitledBorder(""));
		return peer;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		GroupBox component = (GroupBox) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JComponent peer = (JComponent) getPeerManager().getPeer(component);
		TitledBorder border = (TitledBorder) peer.getBorder();
		
		if (GroupBox.TITLE_PROPERTY.equals(name))
		{
			border.setTitle((String) newValue);
		}
		else
		{
			super.propertyChange(event);
		}
	}
}
