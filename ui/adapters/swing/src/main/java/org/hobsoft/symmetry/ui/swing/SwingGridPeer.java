/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingGridPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Grid;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingGridPeer.java 99082 2012-03-08 17:55:52Z mark@IIZUKA.CO.UK $
 */
public class SwingGridPeer extends SwingBoxPeer
{
	// TODO: improve GridBagLayout support :(
	
	// fields -----------------------------------------------------------------
	
	// TODO: no locals!
	private final GridBagConstraints constraints;
	
	// constructors -----------------------------------------------------------
	
	public SwingGridPeer(PeerManager peerManager)
	{
		super(peerManager);
		
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		Container container = (Container) super.createPeer(component);
		container.setLayout(new GridBagLayout());
		return container;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Grid grid = (Grid) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Container container = (Container) getPeerManager().getPeer(grid);
		
		if (Grid.COLUMN_COUNT_PROPERTY.equals(name))
		{
			// TODO: change all constraints!
		}
		if (Box.COMPONENTS_PROPERTY.equals(name))
		{
			container.removeAll();
			
			int columns = grid.getColumnCount();
			
			// TODO: use newValue
			for (Component child : grid)
			{
				int column = container.getComponentCount() % columns;
				constraints.gridwidth = (column == 0) ? GridBagConstraints.RELATIVE : GridBagConstraints.REMAINDER;
				container.add((java.awt.Component) getPeerManager().getPeer(child), constraints);
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
