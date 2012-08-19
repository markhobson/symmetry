/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/PropertyEditorSupport2.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean.editor;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyEditor;

/**
 * Provides support for implementing {@code PropertyEditor}s.
 * <p>
 * This class differs from {@code PropertyEditorSupport} in that {@code setValue} has the following improvements:
 * <ul>
 * <li>it does not fire property change events when the value has not changed
 * <li>it supplies old and new values when firing property change events
 * </ul>
 * 
 * @author Mark Hobson
 * @version $Id: PropertyEditorSupport2.java 97413 2011-12-30 17:52:58Z mark@IIZUKA.CO.UK $
 * @see PropertyEditor
 */
public abstract class PropertyEditorSupport2 implements PropertyEditor
{
	// TODO: should we extend PropertyEditorSupport for interoperability?
	
	// fields -----------------------------------------------------------------

	private Object value;
	
	private PropertyChangeSupport support;
	
	// constructors -----------------------------------------------------------
	
	public PropertyEditorSupport2()
	{
		support = new PropertyChangeSupport(this);
	}

	public PropertyEditorSupport2(Object source)
	{
		support = new PropertyChangeSupport(source);
	}

	// PropertyEditor methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		Object oldValue = this.value;
		this.value = value;
		
		// unlike PropertyEditorSupport:
		// - only fire event when value has actually changed
		// - supply old and new values
		support.firePropertyChange(null, oldValue, this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPaintable()
	{
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintValue(Graphics gfx, Rectangle box)
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJavaInitializationString()
	{
		return "???";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		return String.valueOf(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		throw new IllegalArgumentException(text);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getCustomEditor()
	{
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsCustomEditor()
	{
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}
}
