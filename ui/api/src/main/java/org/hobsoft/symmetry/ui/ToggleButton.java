/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/ToggleButton.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ToggleButton.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
public class ToggleButton extends Button implements Selectable
{
	// fields -----------------------------------------------------------------
	
	private boolean selected;
	
	private ActionListener toggleButtonActionListener = new ToggleButtonActionListener();

	// types ------------------------------------------------------------------
	
	private class ToggleButtonActionListener implements ActionListener
	{
		// ActionListener methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			setSelected(!isSelected());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public ToggleButton()
	{
		this("");
	}
	
	public ToggleButton(String text)
	{
		this(text, false);
	}
	
	public ToggleButton(String text, boolean selected)
	{
		this(text, null, selected);
	}
	
	public ToggleButton(Action action)
	{
		this(action, false);
	}
	
	public ToggleButton(Action action, boolean selected)
	{
		this("", action, selected);
	}
	
	protected ToggleButton(String text, Action action, boolean selected)
	{
		super(text, action);
		setSelected(selected);
		addActionListener(toggleButtonActionListener);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, ToggleButton.class, this, parameter);
	}
	
	// Selectable methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSelected()
	{
		return selected;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSelected(boolean selected)
	{
		boolean oldSelected = this.selected;
		this.selected = selected;
		firePropertyChange(SELECTED_PROPERTY, oldSelected, selected);
	}
}
