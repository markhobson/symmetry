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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.TabBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtTabBoxPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwtTabBoxPeer(PeerManager peerManager)
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
		
		return new TabFolder(parent, SWT.TOP);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		TabBox component = (TabBox) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		TabFolder tabFolder = (TabFolder) getPeerManager().getPeer(component);
		
		if (Deck.SELECTED_INDEX_PROPERTY.equals(name))
		{
			tabFolder.setSelection(((Integer) newValue).intValue());
		}
		else if (Box.COMPONENTS_PROPERTY.equals(name))
		{
			// TODO: update for tabs
//			if (oldValue == null)
//				((SWTPeer) getPeerManager().getPeer(newValue)).setParent(tabFolder);
//			else if (newValue == null)
//				((Widget) getPeerManager().getPeer(oldValue).getWidget()).dispose();
		}
	}
}
