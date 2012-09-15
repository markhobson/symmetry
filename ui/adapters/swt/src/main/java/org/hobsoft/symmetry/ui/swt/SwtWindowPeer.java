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

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.Window;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtWindowPeer extends SwtBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtWindowPeer(PeerManager peerManager)
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
		Shell shell = new Shell(Display.getDefault());
		shell.setLayout(newLayout());
		return shell;
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
		Shell shell = (Shell) getPeerManager().getPeer(component);
		
		if (Window.TITLE_PROPERTY.equals(name))
		{
			shell.setText((String) newValue);
		}
		else if (Window.IMAGE_PROPERTY.equals(name))
		{
			if (newValue != null)
			{
				shell.setImage(SwtUtils.getSWTImage(shell, (Image) newValue));
			}
		}
		else if (Component.VISIBLE_PROPERTY.equals(name))
		{
			// TODO: reimplement
//			setVisible(shell, ((Boolean) newValue).booleanValue());
		}
		else
		{
			super.propertyChange(event);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected void setVisible(Shell shell, boolean visible)
	{
		if (visible)
		{
			// TODO: pack when widgets have sensible preferred sizes
//			shell.pack();
			Display display = shell.getDisplay();
			Rectangle screenBounds = display.getBounds();
			Point shellSize = shell.getSize();
			shell.setLocation((screenBounds.width - shellSize.x) >> 1, (screenBounds.height - shellSize.y) >> 1);
			shell.open();
			
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
			
			display.sleep();
		}
		else
		{
			shell.close();
		}
	}
}
