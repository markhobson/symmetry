/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtGridPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.Window;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtGridPeer.java 99082 2012-03-08 17:55:52Z mark@IIZUKA.CO.UK $
 */
public class SwtGridPeer extends SwtBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtGridPeer(PeerManager peerManager)
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
		Grid component = (Grid) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Composite composite = (Composite) getPeerManager().getPeer(component);
		
		if (Grid.COLUMN_COUNT_PROPERTY.equals(name))
		{
			((GridLayout) composite.getLayout()).numColumns = ((Integer) newValue).intValue();
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
		return new GridLayout();
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
