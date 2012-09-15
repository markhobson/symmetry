/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: SwingBoxPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
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
