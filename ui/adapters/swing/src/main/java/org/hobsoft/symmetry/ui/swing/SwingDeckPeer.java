/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingDeckPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
