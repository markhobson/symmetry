/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtGroupBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.GroupBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtGroupBoxPeer.java 88029 2011-05-13 18:53:16Z mark@IIZUKA.CO.UK $
 */
public class SwtGroupBoxPeer extends SwtBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtGroupBoxPeer(PeerManager peerManager)
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
		
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(newLayout());
		return group;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		GroupBox component = (GroupBox) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Group group = (Group) getPeerManager().getPeer(component);
		
		if (GroupBox.TITLE_PROPERTY.equals(name))
		{
			group.setText((String) newValue);
		}
		else
		{
			super.propertyChange(event);
		}
	}
}
