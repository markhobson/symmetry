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
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Orientation;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingBoxPeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
	private static class Layout extends BoxLayout
	{
		// constants --------------------------------------------------------------
		
		private static final long serialVersionUID = 1L;
		
		// constructors -----------------------------------------------------------
		
		public Layout(Container container, int axis)
		{
			super(container, axis);
		}
		
		// LayoutManager2 methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public synchronized float getLayoutAlignmentX(Container target)
		{
			return 0;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public synchronized float getLayoutAlignmentY(Container target)
		{
			return 0;
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public SwingBoxPeer(PeerManager peerManager)
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
		return new JPanel();
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Box box = (Box) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Container container = getContainer(box);
		
		if (Box.COMPONENTS_PROPERTY.equals(name))
		{
			if (event instanceof IndexedPropertyChangeEvent)
			{
				int index = ((IndexedPropertyChangeEvent) event).getIndex();
				
				if (oldValue == null)
				{
					container.add((java.awt.Component) getPeerManager().getPeer(newValue), index);
				}
				else if (newValue == null)
				{
					container.remove((java.awt.Component) getPeerManager().getPeer(oldValue));
				}
			}
			else
			{
				container.removeAll();
				
				for (Component child : box)
				{
					container.add((java.awt.Component) getPeerManager().getPeer(child));
				}
			}
		}
		else if (Box.ORIENTATION_PROPERTY.equals(name))
		{
			int axis = newValue.equals(Orientation.HORIZONTAL) ? BoxLayout.X_AXIS : BoxLayout.Y_AXIS;
			container.setLayout(new Layout(container, axis));
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected Container getContainer(Box box)
	{
		return (Container) getPeerManager().getPeer(box);
	}
}
