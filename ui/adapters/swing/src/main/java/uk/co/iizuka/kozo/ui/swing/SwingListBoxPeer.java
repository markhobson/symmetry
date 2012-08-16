/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingListBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.SelectionMode;
import uk.co.iizuka.kozo.ui.model.ListModel;
import uk.co.iizuka.kozo.ui.swing.model.ListModelAdapter;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingListBoxPeer.java 97979 2012-01-24 16:18:07Z mark@IIZUKA.CO.UK $
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
