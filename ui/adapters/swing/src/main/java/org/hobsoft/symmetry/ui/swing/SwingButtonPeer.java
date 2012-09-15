/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingButtonPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.hobsoft.symmetry.ui.swing.event.ActionListenerAdapter;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingButtonPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwingButtonPeer(PeerManager peerManager)
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
		return new JButton();
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Button component = (Button) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		AbstractButton peer = (AbstractButton) getPeerManager().getPeer(component);
		
		if (Button.ACTION_LISTENERS_PROPERTY.equals(name))
		{
			for (java.awt.event.ActionListener oldListener : peer.getActionListeners())
			{
				peer.removeActionListener(oldListener);
			}
			
			for (ActionListener newListener : (ActionListener[]) newValue)
			{
				peer.addActionListener(new ActionListenerAdapter(component, newListener));
			}
		}
		else if (Button.IMAGE_PROPERTY.equals(name))
		{
			Icon icon = (newValue != null) ? new ImageIcon(((Image) newValue).getData()) : null;
			
			peer.setIcon(icon);
		}
		else if (Button.ENABLED_PROPERTY.equals(name))
		{
			peer.setEnabled(((Boolean) newValue).booleanValue());
		}
		else if (Button.MNEMONIC_PROPERTY.equals(name))
		{
			peer.setMnemonic(((Integer) newValue).intValue());
		}
		else if (Button.TEXT_PROPERTY.equals(name))
		{
			peer.setText((String) newValue);
		}
		else if (Button.TOOL_TIP_PROPERTY.equals(name))
		{
			peer.setToolTipText((String) newValue);
		}
	}
}
