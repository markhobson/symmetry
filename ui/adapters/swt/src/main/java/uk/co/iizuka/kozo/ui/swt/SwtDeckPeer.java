/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtDeckPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.Window;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtDeckPeer.java 88029 2011-05-13 18:53:16Z mark@IIZUKA.CO.UK $
 */
public class SwtDeckPeer extends SwtBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtDeckPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Deck component = (Deck) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Composite composite = (Composite) getPeerManager().getPeer(component);
		
		if (Deck.SELECTED_INDEX_PROPERTY.equals(name))
		{
			int index = ((Integer) newValue).intValue();
			((StackLayout) composite.getLayout()).topControl = composite.getChildren()[index];
			composite.layout();
		}
		else if (Window.ORIENTATION_PROPERTY.equals(name))
		{
			// override
		}
		else
		{
			super.propertyChange(event);
		}
	}
	
	// SWTBoxPeer methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Layout newLayout()
	{
		return new StackLayout();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateLayoutData(Box box)
	{
		// override
	}
}
