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

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.Window;

/**
 * 
 * 
 * @author Mark Hobson
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
