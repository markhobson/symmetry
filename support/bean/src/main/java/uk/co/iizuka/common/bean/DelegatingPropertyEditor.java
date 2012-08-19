/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/DelegatingPropertyEditor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;

/**
 * A {@code PropertyEditor} implementation that delegates to another.
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingPropertyEditor.java 97399 2011-12-30 11:55:45Z mark@IIZUKA.CO.UK $
 * @see PropertyEditor
 */
abstract class DelegatingPropertyEditor implements PropertyEditor
{
	// fields -----------------------------------------------------------------
	
	private final PropertyEditor delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingPropertyEditor(PropertyEditor delegate)
	{
		this.delegate = delegate;
	}
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		delegate.setValue(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		return delegate.getValue();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPaintable()
	{
		return delegate.isPaintable();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintValue(Graphics gfx, Rectangle box)
	{
		delegate.paintValue(gfx, box);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJavaInitializationString()
	{
		return delegate.getJavaInitializationString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		return delegate.getAsText();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		delegate.setAsText(text);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return delegate.getTags();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getCustomEditor()
	{
		return delegate.getCustomEditor();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsCustomEditor()
	{
		return delegate.supportsCustomEditor();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		delegate.addPropertyChangeListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		delegate.removePropertyChangeListener(listener);
	}
	
	// protected methods ------------------------------------------------------
	
	protected PropertyEditor getDelegate()
	{
		return delegate;
	}
}
