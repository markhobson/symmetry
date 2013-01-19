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
package org.hobsoft.symmetry.swing;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;

import javax.swing.SwingUtilities;

import org.hobsoft.symmetry.DelegatingPeerHandler;
import org.hobsoft.symmetry.PeerHandler;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingPeerHandlerDecorator extends DelegatingPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwingPeerHandlerDecorator(PeerHandler delegate)
	{
		super(delegate);
	}

	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent event)
	{
		if (EventQueue.isDispatchThread())
		{
			super.propertyChange(event);
		}
		else
		{
			SwingUtilities.invokeLater(
				new Runnable()
				{
					@Override
					public void run()
					{
						propertyChange(event);
					}
				}
			);
		}
	}
}
