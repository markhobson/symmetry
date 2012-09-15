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

import javax.swing.JTabbedPane;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingTabBoxPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwingTabBoxPeer(PeerManager peerManager)
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
		return new JTabbedPane();
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		TabBox tabBox = (TabBox) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JTabbedPane tabbedPane = (JTabbedPane) getPeerManager().getPeer(tabBox);
		
		if (Box.COMPONENTS_PROPERTY.equals(name))
		{
			while (tabbedPane.getTabCount() > 0)
			{
				tabbedPane.removeTabAt(0);
			}
			
			for (Tab tab : (Tab[]) newValue)
			{
				int index = tabbedPane.getTabCount();
				tabbedPane.addTab(tab.getText(), (java.awt.Component) getPeerManager().getPeer(tab.getComponent()));
				tabbedPane.setToolTipTextAt(index, tab.getToolTip());
				tabbedPane.setMnemonicAt(index, tab.getMnemonic());
				tab.addPropertyChangeListener(this);
			}
		}
		else if (Deck.SELECTED_INDEX_PROPERTY.equals(name))
		{
			// TODO: fix when selectedIndex fired before tabs are added
			tabbedPane.setSelectedIndex(((Integer) newValue).intValue());
		}
		
		// TODO: listen for tab properties
	}
}
