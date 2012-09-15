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

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.SelectionMode;
import org.hobsoft.symmetry.ui.model.ListModel;
import org.hobsoft.symmetry.ui.swing.model.ListModelAdapter;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingListBoxPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwingListBoxPeer(PeerManager peerManager)
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
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return list;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		ListBox<?> listBox = (ListBox<?>) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JList list = (JList) getPeerManager().getPeer(listBox);
		
		if (ComboBox.MODEL_PROPERTY.equals(name))
		{
			list.setModel(new ListModelAdapter((ListModel<?>) newValue));
		}
		else if (ComboBox.SELECTED_INDEX_PROPERTY.equals(name))
		{
			list.setSelectedIndex(((Integer) newValue).intValue());
		}
		else if (ListBox.VISIBLE_ROW_COUNT_PROPERTY.equals(name))
		{
			list.setVisibleRowCount(((Integer) newValue).intValue());
		}
		else if (ListBox.SELECTION_MODE_PROPERTY.equals(name))
		{
			list.setSelectionMode(toSwingSelectionMode((SelectionMode) newValue));
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static int toSwingSelectionMode(SelectionMode selectionMode)
	{
		int swingSelectionMode;
		
		switch (selectionMode)
		{
			case SINGLE:
				swingSelectionMode = ListSelectionModel.SINGLE_SELECTION;
				break;
				
			case MULTIPLE:
				swingSelectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
				break;
				
			default:
				throw new IllegalArgumentException("Unknown selection mode: " + selectionMode);
		}
		
		return swingSelectionMode;
	}
}
