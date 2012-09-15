/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtLabelPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.Label;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtLabelPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwtLabelPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwtLabelPeer(PeerManager peerManager)
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
		
		return new org.eclipse.swt.widgets.Label(parent, SWT.NONE);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Label component = (Label) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		org.eclipse.swt.widgets.Label label = (org.eclipse.swt.widgets.Label) getPeerManager().getPeer(component);
		
		if (Label.IMAGE_PROPERTY.equals(name))
		{
			label.setImage(SwtUtils.getSWTImage(label, (Image) newValue));
		}
		else if (Label.TEXT_PROPERTY.equals(name))
		{
			label.setText((String) newValue);
		}
		else if (Label.TOOL_TIP_PROPERTY.equals(name))
		{
			label.setToolTipText((String) newValue);
		}
	}
	
	// TODO: reimplement
//	@Override
//	public void asTableItem(TableItem item, int column)
//	{
//		Label label = (Label) getComponent();
//		
//		item.setText(column, label.getText());
//		
//		Image image = label.getImage();
//		if (image != null)
//			item.setImage(SWTUtils.getSWTImage(item, image));
//	}
//	
//	@Override
//	public void asTreeItem(TreeItem item)
//	{
//		Label label = (Label) getComponent();
//		
//		item.setText(label.getText());
//		
//		Image image = label.getImage();
//		if (image != null)
//			item.setImage(SWTUtils.getSWTImage(item, image));
//	}
}
