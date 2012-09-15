/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/swing/src/main/java/uk/co/iizuka/kozo/swing/AbstractSwingPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
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
