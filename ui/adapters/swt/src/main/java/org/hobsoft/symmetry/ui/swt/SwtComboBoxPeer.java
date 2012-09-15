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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
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
public class SwtComboBoxPeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
	private static class ComboSelectionListener implements SelectionListener
	{
		// fields -----------------------------------------------------------------
		
		private final ComboBox<?> comboBox;
		
		// constructors -----------------------------------------------------------
		
		public ComboSelectionListener(ComboBox<?> comboBox)
		{
			this.comboBox = comboBox;
		}
		
		// SelectionListener methods ----------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetSelected(SelectionEvent event)
		{
			Combo combo = (Combo) event.widget;
			comboBox.setSelectedIndex(combo.getSelectionIndex());
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetDefaultSelected(SelectionEvent event)
		{
			widgetSelected(event);
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public SwtComboBoxPeer(PeerManager peerManager)
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
		
		Combo combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.addSelectionListener(new ComboSelectionListener((ComboBox<?>) component));
		return combo;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		ComboBox<?> component = (ComboBox<?>) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Combo combo = (Combo) getPeerManager().getPeer(component);
		
		if (ComboBox.MODEL_PROPERTY.equals(name))
		{
			setModel(combo, (ListModel<?>) newValue);
		}
		else if (ListBox.SELECTED_INDEX_PROPERTY.equals(name))
		{
			int intValue = ((Integer) newValue).intValue();
			
			if (combo.getSelectionIndex() != intValue)
			{
				combo.select(intValue);
			}
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
	
	private static void setModel(Combo combo, ListModel<?> model)
	{
		for (int i = 0; i < model.getItemCount(); i++)
		{
			combo.add(model.getItem(i).toString());
		}
	}
}
