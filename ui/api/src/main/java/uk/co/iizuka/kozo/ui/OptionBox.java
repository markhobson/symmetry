/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/OptionBox.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import java.beans.EventSetDescriptor;

import uk.co.iizuka.common.bean.EventSets;
import uk.co.iizuka.kozo.ui.event.AbstractClosureEventListener;
import uk.co.iizuka.kozo.ui.event.ActionEvent;
import uk.co.iizuka.kozo.ui.event.ActionListener;
import uk.co.iizuka.kozo.ui.event.OptionEvent;
import uk.co.iizuka.kozo.ui.event.OptionListener;
import uk.co.iizuka.kozo.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: OptionBox.java 95601 2011-11-28 12:55:42Z mark@IIZUKA.CO.UK $
 */
public class OptionBox extends VBox
{
	// TODO: generalise finish/cancel buttons to n-buttons
	// TODO: perhaps leave button placement to hydrator and make this a simple container/button container?
	
	// constants --------------------------------------------------------------
	
	public static final String COMPONENT_PROPERTY = "component";
	
	public static final String FINISH_BUTTON_PROPERTY = "finishButton";
	
	public static final String CANCEL_BUTTON_PROPERTY = "cancelButton";
	
	private static final EventSetDescriptor FORM_FINISHED_EVENT = EventSets.getDescriptor(OptionBox.class, "option",
		"formFinished");

	private static final EventSetDescriptor FORM_CANCELLED_EVENT = EventSets.getDescriptor(OptionBox.class, "option",
		"formCancelled");
	
	// types ------------------------------------------------------------------
	
	private static class ClosureOptionListener extends AbstractClosureEventListener<OptionEvent>
		implements OptionListener
	{
		// fields -----------------------------------------------------------------
		
		private final boolean onFinish;

		private final boolean onCancel;
		
		// constructors -----------------------------------------------------------
		
		public ClosureOptionListener(Closure<? super OptionEvent> closure, boolean onFinish, boolean onCancel)
		{
			super(closure);
			
			this.onFinish = onFinish;
			this.onCancel = onCancel;
		}
		
		// OptionListener methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void formFinished(OptionEvent event)
		{
			if (onFinish)
			{
				getClosure().apply(event);
			}
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void formCancelled(OptionEvent event)
		{
			if (onCancel)
			{
				getClosure().apply(event);
			}
		}
	}
	
	private class OptionBoxActionListener implements ActionListener
	{
		// ActionListener methods ---------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			Component source = event.getSource();
			
			if (source == finishButton)
			{
				doFinish();
			}
			else if (source == cancelButton)
			{
				doCancel();
			}
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private Component component;
	
	private Button finishButton;
	
	private Button cancelButton;
	
	private HBox optionBox;
	
	private ActionListener actionListener;
	
	// constructors -----------------------------------------------------------
	
	public OptionBox()
	{
		this(null);
	}
	
	public OptionBox(Component component)
	{
		this(component, new Button("OK"), new Button("Cancel"));
	}
	
	public OptionBox(Component component, Button finishButton, Button cancelButton)
	{
		optionBox = new HBox();
		actionListener = new OptionBoxActionListener();
		
		add(optionBox);
		setComponent(component);
		setFinishButton(finishButton);
		setCancelButton(cancelButton);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addOptionListener(OptionListener listener)
	{
		addListener(OptionListener.class, listener);
	}
	
	public void removeOptionListener(OptionListener listener)
	{
		removeListener(OptionListener.class, listener);
	}
	
	public OptionListener[] getOptionListeners()
	{
		return getListeners(OptionListener.class);
	}
	
	public int getOptionListenerCount()
	{
		return getListenerCount(OptionListener.class);
	}
	
	public OptionBox onOption(Closure<? super OptionEvent> closure)
	{
		addOptionListener(new ClosureOptionListener(closure, true, true));
		
		return this;
	}
	
	public OptionBox onFinish(Closure<? super OptionEvent> closure)
	{
		addOptionListener(new ClosureOptionListener(closure, false, true));
		
		return this;
	}
	
	public OptionBox onCancel(Closure<? super OptionEvent> closure)
	{
		addOptionListener(new ClosureOptionListener(closure, true, false));
		
		return this;
	}
	
	public Component getComponent()
	{
		return component;
	}
	
	public void setComponent(Component component)
	{
		Component oldComponent = this.component;
		this.component = component;
		
		if (oldComponent != null)
		{
			remove(oldComponent);
		}
		
		if (component != null)
		{
			add(0, component);
		}
		
		firePropertyChange(COMPONENT_PROPERTY, oldComponent, component);
	}
	
	public Button getFinishButton()
	{
		return finishButton;
	}
	
	public void setFinishButton(Button finishButton)
	{
		Button oldFinishButton = this.finishButton;
		this.finishButton = finishButton;
		
		if (oldFinishButton != null)
		{
			optionBox.remove(oldFinishButton);
			oldFinishButton.removeActionListener(actionListener);
		}
		
		if (finishButton != null)
		{
			finishButton.addActionListener(actionListener);
			optionBox.add(0, finishButton);
		}
		
		firePropertyChange(FINISH_BUTTON_PROPERTY, oldFinishButton, finishButton);
	}
	
	public Button getCancelButton()
	{
		return cancelButton;
	}
	
	public void setCancelButton(Button cancelButton)
	{
		Button oldCancelButton = this.cancelButton;
		this.cancelButton = cancelButton;
		
		if (oldCancelButton != null)
		{
			optionBox.remove(oldCancelButton);
			oldCancelButton.removeActionListener(actionListener);
		}
		
		if (cancelButton != null)
		{
			cancelButton.addActionListener(actionListener);
			optionBox.add(1, cancelButton);
		}
		
		firePropertyChange(CANCEL_BUTTON_PROPERTY, oldCancelButton, cancelButton);
	}
	
	public void doFinish()
	{
		fireEvent(FORM_FINISHED_EVENT, new OptionEvent(this));
	}
	
	public void doCancel()
	{
		fireEvent(FORM_CANCELLED_EVENT, new OptionEvent(this));
	}
}
