/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtTabBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Widget;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.TabBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtTabBoxPeer.java 98503 2012-02-15 15:07:03Z mark@IIZUKA.CO.UK $
 */
public class SwtTabBoxPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwtTabBoxPeer(PeerManager peerManager)
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
		
		return new TabFolder(parent, SWT.TOP);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		TabBox component = (TabBox) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		TabFolder tabFolder = (TabFolder) getPeerManager().getPeer(component);
		
		if (Deck.SELECTED_INDEX_PROPERTY.equals(name))
		{
			tabFolder.setSelection(((Integer) newValue).intValue());
		}
		else if (Box.COMPONENTS_PROPERTY.equals(name))
		{
			// TODO: update for tabs
//			if (oldValue == null)
//				((SWTPeer) getPeerManager().getPeer(newValue)).setParent(tabFolder);
//			else if (newValue == null)
//				((Widget) getPeerManager().getPeer(oldValue).getWidget()).dispose();
		}
	}
}
