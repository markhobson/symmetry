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
package org.hobsoft.symmetry.swt;

import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;
import org.hobsoft.symmetry.CompositePeerManager;
import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractSwtPeerManager extends CompositePeerManager
{
	// constants --------------------------------------------------------------
	
	private static final Logger LOG = Logger.getLogger(AbstractSwtPeerManager.class.getName());

	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent event)
	{
		Display.getDefault().syncExec(
			new Runnable()
			{
				@Override
				public void run()
				{
					AbstractSwtPeerManager.super.propertyChange(event);
				}
			}
		);
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
