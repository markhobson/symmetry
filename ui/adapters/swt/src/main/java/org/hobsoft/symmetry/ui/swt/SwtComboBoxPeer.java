/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtComboBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: SwtComboBoxPeer.java 97979 2012-01-24 16:18:07Z mark@IIZUKA.CO.UK $
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
