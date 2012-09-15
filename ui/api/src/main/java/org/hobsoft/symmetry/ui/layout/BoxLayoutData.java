/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/layout/BoxLayoutData.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.layout;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.common.base.Objects;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class BoxLayoutData
{
	// constants --------------------------------------------------------------
	
	public static final String LENGTH_PROPERTY = "length";
	
	private static final Length DEFAULT_LENGTH = null;

	// fields -----------------------------------------------------------------
	
	private final PropertyChangeSupport support;
	
	private Length length;
	
	// constructors -----------------------------------------------------------
	
	public BoxLayoutData()
	{
		support = new PropertyChangeSupport(this);
		
		setLength(DEFAULT_LENGTH);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(propertyName, listener);
	}
	
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(propertyName, listener);
	}
	
	public Length getLength()
	{
		return length;
	}
	
	public void setLength(Length length)
	{
		Length oldLength = this.length;
		this.length = length;
		support.firePropertyChange(LENGTH_PROPERTY, oldLength, this.length);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof BoxLayoutData))
		{
			return false;
		}
		
		BoxLayoutData data = (BoxLayoutData) object;
		
		return Objects.equal(length, data.length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return String.format("%s[length=%d]", getClass().getName(), length);
	}
}
