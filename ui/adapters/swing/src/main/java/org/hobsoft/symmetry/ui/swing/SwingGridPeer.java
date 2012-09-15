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
