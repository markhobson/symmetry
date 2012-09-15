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
package org.hobsoft.symmetry.ui.swing.event;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ActionListenerAdapter extends AbstractAdapter implements java.awt.event.ActionListener
{
	// fields -----------------------------------------------------------------
	
	private ActionListener listener;
	
	// constructors -----------------------------------------------------------
	
	public ActionListenerAdapter(Component component, ActionListener listener)
	{
		super(component);
		
		this.listener = listener;
	}
	
	// ActionListener methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(java.awt.event.ActionEvent event)
	{
		listener.actionPerformed(new ActionEvent(getComponent()));
	}
}
