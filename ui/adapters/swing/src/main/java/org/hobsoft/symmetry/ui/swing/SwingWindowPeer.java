/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingWindowPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Window;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingWindowPeer extends SwingBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingWindowPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerHandler methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		return frame;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Window component = (Window) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JFrame frame = (JFrame) getPeerManager().getPeer(component);
		
		if (Window.TITLE_PROPERTY.equals(name))
		{
			frame.setTitle((String) newValue);
		}
		else if (Window.IMAGE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Component.VISIBLE_PROPERTY.equals(name))
		{
			boolean visible = ((Boolean) newValue).booleanValue();
			if (visible)
			{
				frame.pack();
				// TODO: use setLocationByPlatform() when upgrading to jdk1.5
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Dimension frameSize = frame.getSize();
				frame.setLocation((screenSize.width - frameSize.width) >> 1,
					(screenSize.height - frameSize.height) >> 1);
			}
			frame.setVisible(visible);
		}
		else
		{
			super.propertyChange(event);
		}
	}
	
	// SwingBoxPeer methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Container getContainer(Box box)
	{
		return ((JFrame) getPeerManager().getPeer(box)).getContentPane();
	}
}
