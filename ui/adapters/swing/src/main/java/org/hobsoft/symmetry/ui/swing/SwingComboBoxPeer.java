/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingComboBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JComboBox;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.model.ListModel;
import org.hobsoft.symmetry.ui.swing.model.ComboBoxModelAdapter;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingComboBoxPeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
	private static class ComboBoxItemListener implements ItemListener
	{
		// fields -----------------------------------------------------------------
		
		private final ComboBox<?> component;
		
		// constructors -----------------------------------------------------------
		
		public ComboBoxItemListener(ComboBox<?> component)
		{
			this.component = component;
		}
		
		// ItemListener methods ---------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			JComboBox comboBox = (JComboBox) event.getSource();
			component.setSelectedIndex(comboBox.getSelectedIndex());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public SwingComboBoxPeer(PeerManager peerManager)
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
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ComboBoxItemListener((ComboBox<?>) component));
		return comboBox;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Object component = event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JComboBox comboBox = (JComboBox) getPeerManager().getPeer(component);
		
		if (ComboBox.MODEL_PROPERTY.equals(name))
		{
			comboBox.setModel(new ComboBoxModelAdapter((ListModel<?>) newValue));
		}
		else if (ListBox.SELECTED_INDEX_PROPERTY.equals(name))
		{
			int intValue = ((Integer) newValue).intValue();
			
			if (comboBox.getSelectedIndex() != intValue)
			{
				comboBox.setSelectedIndex(intValue);
			}
		}
	}
}
