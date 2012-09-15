/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/swt/src/main/java/uk/co/iizuka/kozo/swt/AbstractSwtPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
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
import org.hobsoft.symmetry.DelegatingPeerManager;
import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractSwtPeerManager.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractSwtPeerManager extends DelegatingPeerManager
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
