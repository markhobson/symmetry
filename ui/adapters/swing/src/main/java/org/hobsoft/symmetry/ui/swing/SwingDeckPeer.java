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

import java.awt.CardLayout;
import java.awt.Container;
import java.beans.PropertyChangeEvent;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Deck;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingDeckPeer extends SwingBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingDeckPeer(PeerManager peerManager)
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
		Container container = (Container) super.createPeer(component);
		container.setLayout(new CardLayout());
		return container;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Deck deck = (Deck) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Container container = (Container) getPeerManager().getPeer(deck);
		CardLayout layout = (CardLayout) container.getLayout();
		
		if (Deck.SELECTED_INDEX_PROPERTY.equals(name))
		{
			layout.show(container, String.valueOf(((Integer) newValue).intValue()));
		}
		else if (Box.COMPONENTS_PROPERTY.equals(name))
		{
			container.removeAll();
			
			// TODO: use newValue
			for (Component child : deck)
			{
				container.add((java.awt.Component) getPeerManager().getPeer(child),
					String.valueOf(container.getComponentCount()));
			}
		}
		else if (Box.ORIENTATION_PROPERTY.equals(name))
		{
			// override
		}
		else
		{
			super.propertyChange(event);
		}
	}
}
