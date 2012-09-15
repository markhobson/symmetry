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
import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.hobsoft.symmetry.DelegatingPeerManager;
import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractSwingPeerManager extends DelegatingPeerManager
{
	// constants --------------------------------------------------------------
	
	private static final Logger LOG = Logger.getLogger(AbstractSwingPeerManager.class.getName());

	// PeerManager methods ----------------------------------------------------
	
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
	
	// protected methods ------------------------------------------------------
	
	protected void initComponent(Object component)
	{
		for (PropertyChangeEvent event : getPropertyChangeEvents(component))
		{
			try
			{
				propertyChange(event);
			}
			catch (Throwable throwable)
			{
				// TODO: handle better
				LOG.log(Level.WARNING, "Error when setting property: " + event.getPropertyName() + " to "
					+ event.getNewValue(), throwable);
			}
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static List<PropertyChangeEvent> getPropertyChangeEvents(Object bean)
	{
		// TODO: move to BeanUtils
		
		BeanInfo info = BeanUtils.getBeanInfo(bean.getClass());
		List<PropertyChangeEvent> changes = new ArrayList<PropertyChangeEvent>();
		
		for (PropertyDescriptor descriptor : info.getPropertyDescriptors())
		{
			if (descriptor.getReadMethod() != null)
			{
				Object newValue = Properties.get(bean, descriptor);
				
				PropertyChangeEvent change = new PropertyChangeEvent(bean, descriptor.getName(), null, newValue);
				
				changes.add(change);
			}
		}
		
		return changes;
	}
}