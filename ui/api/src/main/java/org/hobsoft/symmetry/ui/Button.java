/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Button.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.beans.EventSetDescriptor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.support.bean.EventSets;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.hobsoft.symmetry.ui.event.ClosureActionListener;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.functor.Command;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * A clickable component that can display text, an image, or both.
 * 
 * @author Mark Hobson
 */
public class Button extends Label implements Enableable
{
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean event name that identifies an action being fired on the button.
	 */
	public static final String ACTION_EVENT = "action";
	
	/**
	 * JavaBean property name that identifies a change in the button's action listeners.
	 */
	public static final String ACTION_LISTENERS_PROPERTY = "actionListeners";
	
	/**
	 * JavaBean property name that identifies a change in the button's action.
	 */
	public static final String ACTION_PROPERTY = "action";
	
	/**
	 * JavaBean property name that identifies a change in the button's mnemonic.
	 */
	public static final String MNEMONIC_PROPERTY = "mnemonic";
	
	private static final EventSetDescriptor ACTION_EVENT_SET = EventSets.getDescriptor(Button.class, ACTION_EVENT);
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The action for this button.
	 */
	private Action action;
	
	/**
	 * The enabled state for this button.
	 */
	private boolean enabled;
	
	/**
	 * The mnemonic for this button.
	 */
	private int mnemonic;
	
	/**
	 * The action property change listener for this button.
	 */
	private PropertyChangeListener actionPropertyChangeListener = new ActionPropertyChangeListener();

	// types ------------------------------------------------------------------
	
	/**
	 * A property change listener to synchronize action state with this button.
	 */
	private class ActionPropertyChangeListener implements PropertyChangeListener
	{
		// PropertyChangeListener methods -----------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			String name = event.getPropertyName();
			Object newValue = event.getNewValue();
			
			if (name == Action.NAME_PROPERTY)
			{
				setText((String) newValue);
			}
			else if (name == Action.IMAGE_PROPERTY)
			{
				setImage((Image) newValue);
			}
			else if (name == Action.DESCRIPTION_PROPERTY)
			{
				setToolTip((String) newValue);
			}
			else if (name == Action.MNEMONIC_PROPERTY)
			{
				setMnemonic(((Character) newValue).charValue());
			}
		}
	}
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new button with no text.
	 */
	public Button()
	{
		this("");
	}
	
	/**
	 * Creates a new button with the specified text.
	 * 
	 * @param text
	 *            the text for the button
	 */
	public Button(String text)
	{
		this(text, null);
	}
	
	/**
	 * Creates a new button with the specified image.
	 * 
	 * @param image
	 *            the image for the button
	 */
	public Button(Image image)
	{
		this();
		
		setImage(image);
	}
	
	/**
	 * Creates a new button for the specified action.
	 * 
	 * @param action
	 *            the action for the button
	 */
	public Button(Action action)
	{
		this("", action);
	}
	
	/**
	 * Creates a new button with either the specified text or action.
	 * 
	 * @param text
	 *            the text for the button
	 * @param action
	 *            the action for the button, or {@code null} for none
	 */
	protected Button(String text, Action action)
	{
		super(text);
		setEnabled(true);
		setMnemonic(0);
		
		if (action != null)
		{
			setAction(action);
		}
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, Button.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Adds the specified listener to those that will be notified when this button is clicked.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param listener
	 *            the listener that will be notified
	 */
	public void addActionListener(ActionListener listener)
	{
		addListener(ActionListener.class, listener);
		
		// TODO: fire indexed property change event
	}
	
	/**
	 * Removes the specified listener from those that will be notified when this button is clicked.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param listener
	 *            the listener that will no longer be notified
	 */
	public void removeActionListener(ActionListener listener)
	{
		removeListener(ActionListener.class, listener);
		
		// TODO: fire indexed property change event
	}
	
	/**
	 * Gets an array of all listeners that are notified when this button is clicked.
	 * 
	 * @return an array of all listeners that are notified
	 */
	public ActionListener[] getActionListeners()
	{
		return getListeners(ActionListener.class);
	}
	
	/**
	 * Gets the number of listeners that are notified when this button is clicked.
	 * 
	 * @return the number of listeners that are notified
	 */
	public int getActionListenerCount()
	{
		return getListenerCount(ActionListener.class);
	}
	
	/**
	 * Adds the specified closure to those that will be executed when this button is clicked.
	 * 
	 * @param closure
	 *            the closure that will be executed
	 * @return this button
	 */
	public Button onAction(Closure<? super ActionEvent> closure)
	{
		addActionListener(new ClosureActionListener(closure));
		
		return this;
	}
	
	/**
	 * Gets the keyboard shortcut character used to click this button in combination with the platform's accelerator
	 * key.
	 * 
	 * @return the character as a {@code char}, or {@code 0} for none
	 */
	public int getMnemonic()
	{
		return mnemonic;
	}
	
	/**
	 * Sets the keyboard shortcut character used to click this button in combination with the platform's accelerator
	 * key.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param mnemonic
	 *            the character as a {@code char}, or {@code 0} for none
	 */
	public void setMnemonic(int mnemonic)
	{
		int oldMnemonic = this.mnemonic;
		this.mnemonic = mnemonic;
		firePropertyChange(MNEMONIC_PROPERTY, oldMnemonic, mnemonic);
	}
	
	/**
	 * Gets the action that configures this button's properties.
	 * 
	 * @return the action that configures this button's properties, or {@code null} for none.
	 */
	public Action getAction()
	{
		return action;
	}
	
	/**
	 * Sets the action that configures this button's properties. See the {@code applyAction} method for details of
	 * the configuration used. The specified action will also be notified when this button is clicked.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param action
	 *            the action to configure this button's properties from
	 * @see #applyAction(Action)
	 */
	public void setAction(Action action)
	{
		Action oldAction = this.action;
		this.action = action;
		
		if (oldAction != null)
		{
			action.removePropertyChangeListener(actionPropertyChangeListener);
			removeActionListener(oldAction);
		}
		
		applyAction(action);
		addActionListener(action);
		action.addPropertyChangeListener(actionPropertyChangeListener);
		
		firePropertyChange(ACTION_PROPERTY, oldAction, action);
	}
	
	/**
	 * Clicks this button, notifying all interested listeners.
	 */
	public void doClick()
	{
		fireEvent(ACTION_EVENT_SET, new ActionEvent(this));
	}
	
	public Command click()
	{
		// TODO: introduce ClickCommand if we need to special-case this in a hydrator?
		
		return new Command()
		{
			@Override
			public void execute()
			{
				doClick();
			}
		};
	}
	
	// Enableable methods -----------------------------------------------------
	
	/**
	 * Gets whether this button can be clicked.
	 * 
	 * @return {@code true} if this button can be clicked, otherwise {@code false}
	 */
	@Override
	public boolean isEnabled()
	{
		return enabled;
	}
	
	/**
	 * Sets whether this button can be clicked.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param enabled
	 *            {@code true} if this button can be clicked, otherwise {@code false}
	 */
	@Override
	public void setEnabled(boolean enabled)
	{
		boolean oldEnabled = this.enabled;
		this.enabled = enabled;
		firePropertyChange(ENABLED_PROPERTY, oldEnabled, enabled);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * Returns a string representation of this button and its properties.
	 * 
	 * @return a string representation of this button
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[text=" + getText() + "]";
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Configures this button's properties from the specified action. By default, the following mapping is performed:
	 * <p>
	 * <table>
	 * <tr><th><code>Action</code> Property</th><th>Property</th></tr>
	 * <tr><td><code>name</code></td><td><code>text</code></td></tr>    
	 * <tr><td><code>image</code></td><td><code>image</code></td></tr>    
	 * <tr><td><code>description</code></td><td><code>toolTip</code></td></tr>    
	 * <tr><td><code>mnemonic</code></td><td><code>mnemonic</code></td></tr>    
	 * <tr><td><code>enabled</code></td><td><code>enabled</code></td></tr>
	 * </table>
	 * <p>
	 * This method can be overridden to provide custom mappings.
	 * 
	 * @param action
	 *            the action to configure this button's properties from
	 * @see #setAction(Action)
	 */
	protected void applyAction(Action action)
	{
		setText(action.getName());
		setImage(action.getImage());
		setToolTip(action.getDescription());
		setMnemonic(action.getMnemonic());
		setEnabled(action.isEnabled());
	}
}
