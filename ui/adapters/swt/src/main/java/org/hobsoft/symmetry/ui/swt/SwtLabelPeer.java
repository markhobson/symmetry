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
