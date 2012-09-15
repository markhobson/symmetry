/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/StatePeerManager.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.pool.ComponentObjectFactory;
import org.hobsoft.symmetry.pool.ComponentPool;
import org.hobsoft.symmetry.pool.ComponentPoolException;
import org.hobsoft.symmetry.pool.DefaultComponentPool;
import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class StatePeerManager implements PeerManager, PropertyChangeListener
{
	// fields -----------------------------------------------------------------
	
	private final ThreadLocal<StateSession> sessionLocal;
	
	private ComponentPool componentPool;

	// types ------------------------------------------------------------------
	
	private class DefaultStateSession implements StateSession
	{
		// fields -----------------------------------------------------------------
		
		private final Object root;
		
		private final PropertyChangeEventList eventList;
		
		private State state;
		
		// constructors -----------------------------------------------------------
		
		public DefaultStateSession(Object root)
		{
			this.root = root;
			eventList = new PropertyChangeEventList();
			state = null;
		}
		
		// PropertyChangeListener methods -----------------------------------------
		
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			ensureOpen();
			eventList.addPropertyChangeEvent(event);
			invalidate();
		}
		
		// StateSession methods ---------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getRoot()
		{
			return root;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public State getState()
		{
			ensureOpen();
			
			if (state == null)
			{
				state = new State();
				PropertyChangeEvent[] events = eventList.getPropertyChangeEvents();
				
				for (int i = 0; i < events.length; i++)
				{
					PropertyChangeEvent propertyEvent = events[i];
					Object component = propertyEvent.getSource();
					String propertyName = propertyEvent.getPropertyName();
					PropertyDescriptor property = Properties.getDescriptor(component.getClass(),
						propertyName);
					state.addProperty(new PropertyState(component, property, propertyEvent.getNewValue()));
				}
			}
			
			return state.clone();
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void close()
		{
			ensureOpen();
			try
			{
				rollback();
			}
			finally
			{
				closeSession(this);
			}
		}
		
		// private methods --------------------------------------------------------
		
		private void ensureOpen()
		{
			StateSession session = getSession();
			
			if (session == null)
			{
				throw new IllegalStateException("Session not open");
			}
			
			if (session != this)
			{
				throw new IllegalStateException("Another session is already open");
			}
		}
		
		private void invalidate()
		{
			state = null;
		}
		
		private void rollback()
		{
			PropertyChangeEvent[] events = eventList.getPropertyChangeEvents();
			
			for (int i = events.length - 1; i >= 0; i--)
			{
				PropertyChangeEvent event = events[i];
				Object component = event.getSource();
				PropertyDescriptor property = Properties.getDescriptor(component.getClass(), event.getPropertyName());
				Properties.set(component, property, event.getOldValue());
			}
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public StatePeerManager()
	{
		sessionLocal = new ThreadLocal<StateSession>();
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		BeanUtils.addPropertyChangeListener(component, this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPeer(Object component)
	{
		// TODO: can we use the codec here?
		
		return null;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		StateSession session = getSession();
		
		if (session != null)
		{
			session.propertyChange(event);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public void setComponentClass(Class<?> componentClass)
	{
		componentPool = new DefaultComponentPool(new ComponentObjectFactory(this, componentClass));
	}
	
	public StateSession openSession()
	{
		if (getSession() != null)
		{
			throw new IllegalStateException("Session already open");
		}
		
		Object root;
		try
		{
			root = componentPool.borrowComponent();
		}
		catch (ComponentPoolException exception)
		{
			// TODO: handle better
			throw new RuntimeException(exception);
		}
		
		StateSession session = new DefaultStateSession(root);
		sessionLocal.set(session);
		return session;
	}
	
	// private methods --------------------------------------------------------
	
	private StateSession getSession()
	{
		return sessionLocal.get();
	}
	
	private void closeSession(StateSession session)
	{
		try
		{
			componentPool.returnComponent(session.getRoot());
		}
		catch (ComponentPoolException exception)
		{
			// TODO: handle better
			throw new RuntimeException(exception);
		}
		
		sessionLocal.set(null);
	}
}
