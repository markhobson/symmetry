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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.GroupBox;

/**
 * 
 * 
 * @author Mark Hobson
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
