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

import javax.swing.JLabel;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Label;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingLabelPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwingLabelPeer(PeerManager peerManager)
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
		return new JLabel();
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Label component = (Label) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JLabel label = (JLabel) getPeerManager().getPeer(component);
		
		if (Label.IMAGE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Label.TEXT_PROPERTY.equals(name))
		{
			label.setText((String) newValue);
		}
		else if (Label.TOOL_TIP_PROPERTY.equals(name))
		{
			label.setToolTipText((String) newValue);
		}
	}
}
