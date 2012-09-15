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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.model.ListModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtListBoxPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwtListBoxPeer(PeerManager peerManager)
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
		
		return new List(parent, SWT.BORDER | SWT.SINGLE);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		ListBox<?> component = (ListBox<?>) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		List list = (List) getPeerManager().getPeer(component);
		
		if (ComboBox.MODEL_PROPERTY.equals(name))
		{
			setModel(list, (ListModel<?>) newValue);
		}
		else if (ComboBox.SELECTED_INDEX_PROPERTY.equals(name))
		{
			list.select(((Integer) newValue).intValue());
		}
		else if (ListBox.VISIBLE_ROW_COUNT_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (ListBox.SELECTION_MODE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static void setModel(List list, ListModel<?> model)
	{
		for (int i = 0; i < model.getItemCount(); i++)
		{
			list.add(model.getItem(i).toString());
		}
	}
}
